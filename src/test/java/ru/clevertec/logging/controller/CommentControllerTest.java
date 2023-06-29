package ru.clevertec.logging.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.logging.builder.impl.CommentTestBuilder;
import ru.clevertec.logging.builder.impl.NewsTestBuilder;
import ru.clevertec.news.controller.CommentController;
import ru.clevertec.news.entity.Comment;
import ru.clevertec.news.entity.News;
import ru.clevertec.news.service.NewsService;

import static org.mockito.Mockito.doReturn;

@SpringBootTest(classes = {CommentController.class})
@WebFluxTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private WebTestClient client;
    private static final CommentTestBuilder COMMENT_TEST_BUILDER = CommentTestBuilder.aComment();

    @MockBean
    @Qualifier(value = "commentProxy")
    private NewsService service;

    @Test
    public void findByIdShouldReturnStatus200() {
        Comment extend = COMMENT_TEST_BUILDER.build();

        doReturn(extend)
                .when(service)
                .getById(extend.getId());

        client.get()
                .uri("/comments" + extend.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isFound()
                .expectBody(Comment.class)
                .isEqualTo(extend);
    }

}
