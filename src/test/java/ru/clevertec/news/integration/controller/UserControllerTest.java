package ru.clevertec.news.integration.controller;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.exception_handler.exception.NotFoundException;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.exception_handler.model.ErrorMessage;
import ru.clevertec.news.builder.impl.UserTestBuilder;
import ru.clevertec.news.entity.User;
import ru.clevertec.news.service.UserService;
import ru.clevertec.news.util.Mapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WireMockTest(httpPort = 9889)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static final User USER_TEST_BUILDER = UserTestBuilder.aUser().build();

    @MockBean
    @Qualifier(value = "userProxy")
    private UserService service;

    @Nested
    class FindByIdTest{

        @Test
        public void findByIdShouldReturnStatus302() throws Exception {
            User extend = USER_TEST_BUILDER;

            doReturn(extend)
                    .when(service)
                    .getById(extend.getId());

            mockMvc.perform(get("/users/" + extend.getId()))
                    .andExpect(status().isFound())
                    .andExpect(content().json(Mapper.toString(extend)));
        }

        @Test
        public void findByIdShouldReturnNotFoundException() throws Exception {
            long id = 5L;
            String expectedError = "User with id - " + id + " not found";
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(expectedError)
                    .build();

            doThrow(new NotFoundException(expectedError))
                    .when(service)
                    .getById(id);

            mockMvc.perform(get("/users/" + id))
                    .andExpect(status().isNotFound())
                    .andExpect(content().json(Mapper.toString(error)));
        }

    }

    @Nested
    class GetAllTest {

        @Test
        void getAllShouldReturnList() throws Exception {
            Page<User> page = new PageImpl<>(List.of(USER_TEST_BUILDER));

            doReturn(page)
                    .when(service)
                    .getAll(any(PageRequest.class));

            mockMvc.perform(get("/users"))
                    .andExpect(status().isFound())
                    .andExpect(content().json(Mapper.toString(page)));

        }

        @Test
        void testShouldReturnExceptionNotFound() throws Exception {
            String expectedError = "User not found";
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(expectedError)
                    .build();

            doThrow(new NotFoundException(expectedError))
                    .when(service)
                    .getAll(any(PageRequest.class));

            mockMvc.perform(get("/users"))
                    .andExpect(status().isNotFound())
                    .andExpect(content().json(Mapper.toString(error)));
        }

    }

    @Nested
    @WithMockUser(username = "spring-test", roles = {"ADMIN"})
    class CreateTest {

        @Test
        void createUserShouldReturnUser() throws Exception {
            User expected = USER_TEST_BUILDER;

            doReturn(expected)
                    .when(service)
                    .create(expected);

            mockMvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(expected)))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(Mapper.toString(expected)));
        }

        @Test
        void createUserShouldReturnException() throws Exception {
            String expectedError = "Error with Insert";
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(expectedError)
                    .build();

            doThrow(new ServerErrorException(expectedError))
                    .when(service)
                    .create(USER_TEST_BUILDER);

            mockMvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(USER_TEST_BUILDER)))
                    .andExpect(status().isInternalServerError())
                    .andExpect(content().json(Mapper.toString(error)));
        }

    }

    @Nested
    @WithMockUser(username = "spring-test", roles = {"ADMIN"})
    class DeleteTest {

        @Test
        void deleteUserShouldReturnUser() throws Exception {
            User expected = USER_TEST_BUILDER;

            doReturn(expected)
                    .when(service)
                    .delete(expected);

            mockMvc.perform(delete("/users")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(expected)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(Mapper.toString(expected)));
        }

        @Test
        void deleteUserShouldReturnExceptionNotFound() throws Exception {
            long id = USER_TEST_BUILDER.getId();
            String expectedError = "User with id - " + id + " not found";
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(expectedError)
                    .build();

            doThrow(new NotFoundException(expectedError))
                    .when(service)
                    .delete(USER_TEST_BUILDER);

            mockMvc.perform(delete("/users")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(USER_TEST_BUILDER)))
                    .andExpect(status().isNotFound())
                    .andExpect(content().json(Mapper.toString(error)));
        }

        @Test
        void deleteUserShouldReturnServerErrorException() throws Exception {
            String expectedError = "Error with Delete user";

            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(expectedError)
                    .build();

            doThrow(new ServerErrorException(expectedError))
                    .when(service)
                    .delete(USER_TEST_BUILDER);

            mockMvc.perform(delete("/users")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(USER_TEST_BUILDER)))
                    .andExpect(status().isInternalServerError())
                    .andExpect(content().json(Mapper.toString(error)));
        }

    }

    @Nested
    @WithMockUser(username = "spring-test", roles = {"ADMIN"})
    class UpdateTest {

        @Test
        void updateUserShouldReturnUser() throws Exception {
            User expected = USER_TEST_BUILDER;

            doReturn(expected)
                    .when(service)
                    .update(expected);

            mockMvc.perform(patch("/users")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(expected)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(Mapper.toString(expected)));
        }


        @Test
        void updateUserShouldReturnExceptionNotFound() throws Exception {
            long id = USER_TEST_BUILDER.getId();
            String expectedError = "User with id - " + id + " not found";
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(expectedError)
                    .build();

            doThrow(new NotFoundException(expectedError))
                    .when(service)
                    .update(USER_TEST_BUILDER);

            mockMvc.perform(patch("/users")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(USER_TEST_BUILDER)))
                    .andExpect(status().isNotFound())
                    .andExpect(content().json(Mapper.toString(error)));
        }

        @Test
        void updateUserShouldReturnServerErrorException() throws Exception {
            String expectedError = "Error with Update user:";

            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(expectedError)
                    .build();

            doThrow(new ServerErrorException(expectedError))
                    .when(service)
                    .update(USER_TEST_BUILDER);

            mockMvc.perform(patch("/users")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(USER_TEST_BUILDER)))
                    .andExpect(status().isInternalServerError())
                    .andExpect(content().json(Mapper.toString(error)));
        }

    }

    @Nested
    class FindAllByTest {

        @Test
        void getAllShouldReturnList() throws Exception {
            String findWord = "Alivit";
            List<User> list = List.of(USER_TEST_BUILDER);

            doReturn(list)
                    .when(service)
                    .findAllBy(findWord);

            mockMvc.perform(get("/users/find").param("word", findWord))
                    .andExpect(status().isFound())
                    .andExpect(content().json(Mapper.toString(list)));
        }

        @Test
        void testShouldReturnEmptyList() throws Exception {
            String emptyFindWord = "";
            List<User> list = List.of(USER_TEST_BUILDER);

            doReturn(list)
                    .when(service)
                    .findAllBy(emptyFindWord);

            mockMvc.perform(get("/users/find").param("word", emptyFindWord))
                    .andExpect(status().isFound())
                    .andExpect(content().json(Mapper.toString(list)));
        }

        @Test
        void testShouldReturnNotFoundException() throws Exception {
            String expectedError = "Users Not Found";;
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(expectedError)
                    .build();

            doThrow(new NotFoundException(expectedError))
                    .when(service)
                    .findAllBy("");

            mockMvc.perform(get("/users/find").param("word", ""))
                    .andExpect(status().isNotFound())
                    .andExpect(content().json(Mapper.toString(error)));
        }

    }
    
}
