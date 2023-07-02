package ru.clevertec.news.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.clevertec.exception_handler.exception.NotFoundException;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.news.builder.impl.CommentTestBuilder;
import ru.clevertec.news.dao.CommentRepository;
import ru.clevertec.news.entity.Comment;
import ru.clevertec.news.service.impl.CommentServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository repository;

    private CommentService service;

    private final Comment COMMENT_TEST_BUILDER = CommentTestBuilder.aComment().build();

    @BeforeEach
    void init() {
        service = new CommentServiceImpl(repository);
    }

    @Nested
    class FindByIdTest {

        @Test
        void FindByIdShouldReturnComment() {
            Comment expected = COMMENT_TEST_BUILDER;
            long id = expected.getId();

            doReturn(Optional.of(expected))
                    .when(repository)
                    .findById(id);


            Comment actual = service.getById(id);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void findByIdShouldReturnThrowException() {
            long id = 2L;

            doThrow(new NotFoundException(""))
                    .when(repository)
                    .findById(id);

            assertThrows(NotFoundException.class, () -> repository.findById(id));
        }

        @Test
        void findByIdShouldReturnThrowExceptionWithMessage() {
            long id = 5L;
            String expectedError = "Comment with id - " + id + " not found";

            doThrow(new NotFoundException(expectedError))
                    .when(repository)
                    .findById(id);

            Exception exception = assertThrows(NotFoundException.class,
                    () -> repository.findById(id));
            String actualError = exception.getMessage();


            assertThat(actualError).isEqualTo(expectedError);
        }

    }

    @Nested
    class GetAllTest {

        @Test
        void getAllShouldReturnListOfSizeOne() {

            int expectedSize = 1;
            Pageable pageable = PageRequest.of(1, 1);
            Page<Comment> page = new PageImpl<>(List.of(COMMENT_TEST_BUILDER));

            doReturn(page)
                    .when(repository)
                    .findAll(any(PageRequest.class));

            Page<Comment> actualValues = service.getAll(pageable);

            assertThat(actualValues).hasSize(expectedSize);
        }

        @Test
        void testShouldReturnListWithContainsComment() {
            Comment expected = COMMENT_TEST_BUILDER;
            Pageable pageable = PageRequest.of(1, 1);
            Page<Comment> page = new PageImpl<>(List.of(expected));

            doReturn(page)
                    .when(repository)
                    .findAll(any(PageRequest.class));

            Page<Comment> actualValues = service.getAll(pageable);

            assertThat(actualValues).contains(expected);
        }

        @Test
        void testShouldReturnExceptionNotFound() {
            Pageable pageable = PageRequest.of(1, 1);
            String expectedError = "Comment not found";

            doThrow(new NotFoundException(expectedError))
                    .when(repository)
                    .findAll(any(PageRequest.class));

            Exception exception = assertThrows(NotFoundException.class,
                    () -> service.getAll(pageable));
            String actualError = exception.getMessage();

            assertThat(actualError).contains(expectedError);
        }

    }

    @Nested
    class CreateTest {

        @Test
        void createCommentShouldReturnComment() {
            Comment expected = COMMENT_TEST_BUILDER;
            long id = expected.getId();

            doReturn(expected)
                    .when(repository)
                    .save(expected);

            doReturn(Optional.of(expected))
                    .when(repository)
                    .findById(id);

            Comment actual = service.create(expected);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void createCommentShouldReturnException() {
            String expectedError = "Error with Insert";

            lenient()
                    .doThrow(new ServerErrorException("Error with Insert"))
                    .when(repository)
                    .save(COMMENT_TEST_BUILDER);

            Exception exception = assertThrows(ServerErrorException.class,
                    () -> service.create(COMMENT_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).contains(expectedError);
        }

    }

    @Nested
    class DeleteTest {

        @Test
        void deleteCommentShouldReturnComment() {
            Comment expected = COMMENT_TEST_BUILDER;
            long id = expected.getId();

            doNothing()
                    .when(repository)
                    .delete(expected);

            doReturn(Optional.of(expected))
                    .when(repository)
                    .findById(id);

            Comment actual = service.delete(expected);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void deleteCommentShouldReturnExceptionNotFound() {
            long id = COMMENT_TEST_BUILDER.getId();
            String expectedError = "Comment with id - " + id + " not found";

            lenient()
                    .doThrow(new NotFoundException(expectedError))
                    .when(repository)
                    .findById(id);

            doNothing()
                    .when(repository)
                    .delete(COMMENT_TEST_BUILDER);

            Exception exception = assertThrows(NotFoundException.class,
                    () -> service.delete(COMMENT_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).isEqualTo(expectedError);
        }

        @Test
        void deleteCommentShouldReturnServerErrorException() {
            String expectedError = "Error with Delete comment";

            Exception exception = assertThrows(ServerErrorException.class,
                    () -> service.delete(COMMENT_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).contains(expectedError);
        }

    }

    @Nested
    class UpdateTest {

        @Test
        void updateCommentShouldReturnComment() {
            Comment expected = COMMENT_TEST_BUILDER;
            long id = expected.getId();

            doReturn(expected)
                    .when(repository)
                    .save(expected);

            doReturn(Optional.of(expected))
                    .when(repository)
                    .findById(id);

            Comment actual = service.update(expected);

            assertThat(actual).isEqualTo(expected);
        }


        @Test
        void updateCommentShouldReturnExceptionNotFound() {
            long id = COMMENT_TEST_BUILDER.getId();
            String expectedError = "Comment with id - " + id + " not found";

            lenient()
                    .doThrow(new NotFoundException(expectedError))
                    .when(repository)
                    .findById(id);

            doNothing()
                    .when(repository)
                    .save(COMMENT_TEST_BUILDER);

            Exception exception = assertThrows(NotFoundException.class,
                    () -> service.update(COMMENT_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).isEqualTo(expectedError);
        }

        @Test
        void updateCommentShouldReturnServerErrorException() {
            String expectedError = "Error with Update comment:";

            Exception exception = assertThrows(ServerErrorException.class,
                    () -> service.update(COMMENT_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).contains(expectedError);
        }

    }

    @Nested
    class FindAllBy {

        @Test
        void getAllShouldReturnListOfSizeOne() {
            String findWord = "Alivit";
            int expectedSize = 1;
            List<Comment> list = List.of(COMMENT_TEST_BUILDER);

            doReturn(list)
                    .when(repository)
                    .findAll();

            List<Comment> actualValues = service.findAllBy(findWord);

            assertThat(actualValues).hasSize(expectedSize);
        }

        @Test
        void testShouldReturnExceptionNotFound() {
            String expectedError = "Comment not found";

            doThrow(new NotFoundException(expectedError))
                    .when(repository)
                    .findAll();

            Exception exception = assertThrows(NotFoundException.class,
                    () -> service.findAllBy(""));
            String actualError = exception.getMessage();

            assertThat(actualError).contains(expectedError);
        }

    }
}
