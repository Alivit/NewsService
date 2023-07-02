package ru.clevertec.news.integration.controller;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.exception_handler.model.ErrorMessage;
import ru.clevertec.news.builder.impl.UserTestBuilder;
import ru.clevertec.news.entity.User;
import ru.clevertec.news.service.UserService;
import ru.clevertec.news.util.Mapper;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WireMockTest(httpPort = 9889)
public class AuthorizationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static final User USER_TEST_BUILDER = UserTestBuilder.aUser().build();

    @MockBean
    @Qualifier(value = "userProxy")
    private UserService service;

    @Nested
    class RegistrationTest{

        @Test
        void registryUserShouldReturnUser() throws Exception {
            User expected = USER_TEST_BUILDER;

            doReturn(expected)
                    .when(service)
                    .create(expected);

            mockMvc.perform(post("/registration")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(expected)))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(Mapper.toString(expected)));
        }

        @Test
        void registryUserShouldReturnException() throws Exception {
            String expectedError = "Error with Insert";
            ErrorMessage error = ErrorMessage.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(expectedError)
                    .build();

            doThrow(new ServerErrorException(expectedError))
                    .when(service)
                    .create(USER_TEST_BUILDER);

            mockMvc.perform(post("/registration")
                            .contentType(APPLICATION_JSON)
                            .content(Mapper.toString(USER_TEST_BUILDER)))
                    .andExpect(status().isInternalServerError())
                    .andExpect(content().json(Mapper.toString(error)));
        }
    }
}
