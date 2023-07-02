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
import ru.clevertec.news.builder.impl.NewsTestBuilder;
import ru.clevertec.news.dao.NewsRepository;
import ru.clevertec.news.entity.News;
import ru.clevertec.news.service.impl.NewsServiceImpl;

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
public class NewsServiceTest {

    @Mock
    private NewsRepository repository;

    private NewsService service;

    private final News NEWS_TEST_BUILDER = NewsTestBuilder.aNews().build();

    @BeforeEach
    void init() {
        service = new NewsServiceImpl(repository);
    }

    @Nested
    class FindByIdTest {

        @Test
        void FindByIdShouldReturnNews() {
            News expected = NEWS_TEST_BUILDER;
            long id = expected.getId();

            doReturn(Optional.of(expected))
                    .when(repository)
                    .findById(id);


            News actual = service.getById(id);

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
            String expectedError = "News with id - " + id + " not found";

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
            Page<News> page = new PageImpl<>(List.of(NEWS_TEST_BUILDER));

            doReturn(page)
                    .when(repository)
                    .findAll(any(PageRequest.class));

            Page<News> actualValues = service.getAll(pageable);

            assertThat(actualValues).hasSize(expectedSize);
        }

        @Test
        void testShouldReturnListWithContainsNews() {
            News expected = NEWS_TEST_BUILDER;
            Pageable pageable = PageRequest.of(1, 1);
            Page<News> page = new PageImpl<>(List.of(expected));

            doReturn(page)
                    .when(repository)
                    .findAll(any(PageRequest.class));

            Page<News> actualValues = service.getAll(pageable);

            assertThat(actualValues).contains(expected);
        }

        @Test
        void testShouldReturnExceptionNotFound() {
            Pageable pageable = PageRequest.of(1, 1);
            String expectedError = "News not found";

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
        void createNewsShouldReturnNews() {
            News expected = NEWS_TEST_BUILDER;
            long id = expected.getId();

            doReturn(expected)
                    .when(repository)
                    .save(expected);

            doReturn(Optional.of(expected))
                    .when(repository)
                    .findById(id);

            News actual = service.create(expected);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void createNewsShouldReturnException() {
            String expectedError = "Error with Insert";

            lenient()
                    .doThrow(new ServerErrorException("Error with Insert"))
                    .when(repository)
                    .save(NEWS_TEST_BUILDER);

            Exception exception = assertThrows(ServerErrorException.class,
                    () -> service.create(NEWS_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).contains(expectedError);
        }

    }

    @Nested
    class DeleteTest {

        @Test
        void deleteNewsShouldReturnNews() {
            News expected = NEWS_TEST_BUILDER;
            long id = expected.getId();

            doNothing()
                    .when(repository)
                    .delete(expected);

            doReturn(Optional.of(expected))
                    .when(repository)
                    .findById(id);

            News actual = service.delete(expected);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void deleteNewsShouldReturnExceptionNotFound() {
            long id = NEWS_TEST_BUILDER.getId();
            String expectedError = "News with id - " + id + " not found";

            lenient()
                    .doThrow(new NotFoundException(expectedError))
                    .when(repository)
                    .findById(id);

            doNothing()
                    .when(repository)
                    .delete(NEWS_TEST_BUILDER);

            Exception exception = assertThrows(NotFoundException.class,
                    () -> service.delete(NEWS_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).isEqualTo(expectedError);
        }

        @Test
        void deleteNewsShouldReturnServerErrorException() {
            String expectedError = "Error with Delete news";

            Exception exception = assertThrows(ServerErrorException.class,
                    () -> service.delete(NEWS_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).contains(expectedError);
        }

    }

    @Nested
    class UpdateTest {

        @Test
        void updateNewsShouldReturnNews() {
            News expected = NEWS_TEST_BUILDER;
            long id = expected.getId();

            doReturn(expected)
                    .when(repository)
                    .save(expected);

            doReturn(Optional.of(expected))
                    .when(repository)
                    .findById(id);

            News actual = service.update(expected);

            assertThat(actual).isEqualTo(expected);
        }


        @Test
        void updateNewsShouldReturnExceptionNotFound() {
            long id = NEWS_TEST_BUILDER.getId();
            String expectedError = "News with id - " + id + " not found";

            lenient()
                    .doThrow(new NotFoundException(expectedError))
                    .when(repository)
                    .findById(id);

            doNothing()
                    .when(repository)
                    .save(NEWS_TEST_BUILDER);

            Exception exception = assertThrows(NotFoundException.class,
                    () -> service.update(NEWS_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).isEqualTo(expectedError);
        }

        @Test
        void updateNewsShouldReturnServerErrorException() {
            String expectedError = "Error with Update news:";

            Exception exception = assertThrows(ServerErrorException.class,
                    () -> service.update(NEWS_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).contains(expectedError);
        }

    }

    @Nested
    class FindAllBy {

        @Test
        void getAllShouldReturnListOfSizeOne() {
            String findWord = "Amazon";
            int expectedSize = 1;
            List<News> list = List.of(NEWS_TEST_BUILDER);

            doReturn(list)
                    .when(repository)
                    .findAll();

            List<News> actualValues = service.findAllBy(findWord);

            assertThat(actualValues).hasSize(expectedSize);
        }

        @Test
        void testShouldReturnExceptionNotFound() {
            String expectedError = "News not found";

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
