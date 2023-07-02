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
import ru.clevertec.news.builder.impl.NewsTestBuilder;
import ru.clevertec.news.entity.News;
import ru.clevertec.news.service.NewsService;
import ru.clevertec.news.util.Mapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WireMockTest(httpPort = 9889)
public class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static final News NEWS_TEST_BUILDER = NewsTestBuilder.aNews().build();

    @MockBean
    @Qualifier(value = "newsProxy")
    private NewsService service;

    @Nested
    class FindByIdTest{

        @Test
        public void findByIdShouldReturnStatus302() throws Exception {
            News extend = NEWS_TEST_BUILDER;

            doReturn(extend)
                    .when(service)
                    .getById(extend.getId());

            mockMvc.perform(get("/news/" + extend.getId()))
                    .andExpect(status().isFound())
                    .andExpect(content().json(Mapper.toString(extend)));
        }

        @Test
        public void findByIdShouldReturnNotFoundException() throws Exception {
            long id = 5L;
            String expectedError = "News with id - " + id + " not found";
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(expectedError)
                    .build();

            doThrow(new NotFoundException(expectedError))
                    .when(service)
                    .getById(id);

            mockMvc.perform(get("/news/" + id))
                    .andExpect(status().isNotFound())
                    .andExpect(content().json(Mapper.toString(error)));
        }

    }

    @Nested
    class GetAllTest {

        @Test
        void getAllShouldReturnList() throws Exception {
            Page<News> page = new PageImpl<>(List.of(NEWS_TEST_BUILDER));

            doReturn(page)
                    .when(service)
                    .getAll(any(PageRequest.class));

            mockMvc.perform(get("/news"))
                    .andExpect(status().isFound())
                    .andExpect(content().json(Mapper.toString(page)));

        }

        @Test
        void testShouldReturnExceptionNotFound() throws Exception {
            String expectedError = "News not found";
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(expectedError)
                    .build();

            doThrow(new NotFoundException(expectedError))
                    .when(service)
                    .getAll(any(PageRequest.class));

            mockMvc.perform(get("/news"))
                    .andExpect(status().isNotFound())
                    .andExpect(content().json(Mapper.toString(error)));
        }

    }

    @Nested
    @WithMockUser(username = "spring-test", roles = {"JOURNALIST"})
    class CreateTest {

        @Test
        void createNewsShouldReturnNews() throws Exception {
            News expected = NEWS_TEST_BUILDER;

            doReturn(expected)
                    .when(service)
                    .create(expected);

            mockMvc.perform(post("/news")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(NEWS_TEST_BUILDER)))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(Mapper.toString(expected)));
        }

        @Test
        void createNewsShouldReturnException() throws Exception {
            String expectedError = "Error with Insert";
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(expectedError)
                    .build();

            doThrow(new ServerErrorException(expectedError))
                    .when(service)
                    .create(NEWS_TEST_BUILDER);

            mockMvc.perform(post("/news")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(NEWS_TEST_BUILDER)))
                    .andExpect(status().isInternalServerError())
                    .andExpect(content().json(Mapper.toString(error)));
        }

    }

    @Nested
    @WithMockUser(username = "spring-test", roles = {"JOURNALIST"})
    class DeleteTest {

        @Test
        void deleteNewsShouldReturnNews() throws Exception {
            News expected = NEWS_TEST_BUILDER;

            doReturn(expected)
                    .when(service)
                    .delete(expected);

            mockMvc.perform(delete("/news")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(expected)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(Mapper.toString(expected)));
        }

        @Test
        void deleteNewsShouldReturnExceptionNotFound() throws Exception {
            long id = NEWS_TEST_BUILDER.getId();
            String expectedError = "News with id - " + id + " not found";
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(expectedError)
                    .build();

            doThrow(new NotFoundException(expectedError))
                    .when(service)
                    .delete(NEWS_TEST_BUILDER);

            mockMvc.perform(delete("/news")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(NEWS_TEST_BUILDER)))
                    .andExpect(status().isNotFound())
                    .andExpect(content().json(Mapper.toString(error)));
        }

        @Test
        void deleteNewsShouldReturnServerErrorException() throws Exception {
            String expectedError = "Error with Delete news";

            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(expectedError)
                    .build();

            doThrow(new ServerErrorException(expectedError))
                    .when(service)
                    .delete(NEWS_TEST_BUILDER);

            mockMvc.perform(delete("/news")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(NEWS_TEST_BUILDER)))
                    .andExpect(status().isInternalServerError())
                    .andExpect(content().json(Mapper.toString(error)));
        }

    }

    @Nested
    @WithMockUser(username = "spring-test", roles = {"JOURNALIST"})
    class UpdateTest {

        @Test
        void updateNewsShouldReturnNews() throws Exception {
            News expected = NEWS_TEST_BUILDER;

            doReturn(expected)
                    .when(service)
                    .update(expected);

            mockMvc.perform(patch("/news")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(expected)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(Mapper.toString(expected)));
        }


        @Test
        void updateNewsShouldReturnExceptionNotFound() throws Exception {
            News expected = NEWS_TEST_BUILDER;
            String expectedError = "News with id - " + expected.getId() + " not found";
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(expectedError)
                    .build();

            doThrow(new NotFoundException(expectedError))
                    .when(service)
                    .update(NEWS_TEST_BUILDER);

            mockMvc.perform(patch("/news")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(expected)))
                    .andExpect(status().isNotFound())
                    .andExpect(content().json(Mapper.toString(error)));
        }

        @Test
        void updateNewsShouldReturnServerErrorException() throws Exception {
            String expectedError = "Error with Update news:";

            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(expectedError)
                    .build();

            doThrow(new ServerErrorException(expectedError))
                    .when(service)
                    .update(NEWS_TEST_BUILDER);

            mockMvc.perform(patch("/news")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(NEWS_TEST_BUILDER))
                    )
                    .andExpect(status().isInternalServerError())
                    .andExpect(content().json(Mapper.toString(error)));
        }

    }

    @Nested
    class FindAllByTest {

        @Test
        void getAllShouldReturnList() throws Exception {
            String findWord = "Vitaliy";
            List<News> list = List.of(NEWS_TEST_BUILDER);

            doReturn(list)
                    .when(service)
                    .findAllBy(findWord);

            mockMvc.perform(get("/news/find").param("word", findWord))
                    .andExpect(status().isFound())
                    .andExpect(content().json(Mapper.toString(list)));
        }

        @Test
        void testShouldReturnEmptyList() throws Exception {
            String emptyFindWord = "";
            List<News> list = List.of(NEWS_TEST_BUILDER);

            doReturn(list)
                    .when(service)
                    .findAllBy(emptyFindWord);

            mockMvc.perform(get("/news/find").param("word", emptyFindWord))
                    .andExpect(status().isFound())
                    .andExpect(content().json(Mapper.toString(list)));
        }

        @Test
        void testShouldReturnNotFoundException() throws Exception {
            String expectedError = "News Not Found";;
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(expectedError)
                    .build();

            doThrow(new NotFoundException(expectedError))
                    .when(service)
                    .findAllBy("");

            mockMvc.perform(get("/news/find").param("word", ""))
                    .andExpect(status().isNotFound())
                    .andExpect(content().json(Mapper.toString(error)));
        }

    }

}
