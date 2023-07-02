package ru.clevertec.news.service;

import lombok.RequiredArgsConstructor;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.clevertec.exception_handler.exception.NotFoundException;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.news.builder.impl.UserTestBuilder;
import ru.clevertec.news.dao.UserRepository;
import ru.clevertec.news.entity.User;
import ru.clevertec.news.service.impl.UserServiceImpl;

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
@RequiredArgsConstructor
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    private UserService service;

    private final User USER_TEST_BUILDER = UserTestBuilder.aUser().build();

    @BeforeEach
    void init() {
        service = new UserServiceImpl(repository, new BCryptPasswordEncoder(8));
    }

    @Nested
    class FindByIdTest {

        @Test
        void FindByIdShouldReturnUser() {
            User expected = USER_TEST_BUILDER;
            long id = expected.getId();

            doReturn(Optional.of(expected))
                    .when(repository)
                    .findById(id);


            User actual = service.getById(id);

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
            String expectedError = "User with id - " + id + " not found";

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
            Page<User> page = new PageImpl<>(List.of(USER_TEST_BUILDER));

            doReturn(page)
                    .when(repository)
                    .findAll(any(PageRequest.class));

            Page<User> actualValues = service.getAll(pageable);

            assertThat(actualValues).hasSize(expectedSize);
        }

        @Test
        void testShouldReturnListWithContainsUser() {
            User expected = USER_TEST_BUILDER;
            Pageable pageable = PageRequest.of(1, 1);
            Page<User> page = new PageImpl<>(List.of(expected));

            doReturn(page)
                    .when(repository)
                    .findAll(any(PageRequest.class));

            Page<User> actualValues = service.getAll(pageable);

            assertThat(actualValues).contains(expected);
        }

        @Test
        void testShouldReturnExceptionNotFound() {
            Pageable pageable = PageRequest.of(1, 1);
            String expectedError = "User not found";

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
        void createUserShouldReturnUser() {
            User expected = USER_TEST_BUILDER;
            long id = expected.getId();

            doReturn(expected)
                    .when(repository)
                    .save(expected);

            doReturn(Optional.of(expected))
                    .when(repository)
                    .findById(id);

            User actual = service.create(expected);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void createUserShouldReturnException() {
            String expectedError = "Error with Insert";

            lenient()
                    .doThrow(new ServerErrorException("Error with Insert"))
                    .when(repository)
                    .save(USER_TEST_BUILDER);

            Exception exception = assertThrows(ServerErrorException.class,
                    () -> service.create(USER_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).contains(expectedError);
        }

    }

    @Nested
    class DeleteTest {

        @Test
        void deleteUserShouldReturnUser() {
            User expected = USER_TEST_BUILDER;
            long id = expected.getId();

            doNothing()
                    .when(repository)
                    .delete(expected);

            doReturn(Optional.of(expected))
                    .when(repository)
                    .findById(id);

            User actual = service.delete(expected);

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void deleteUserShouldReturnExceptionNotFound() {
            long id = USER_TEST_BUILDER.getId();
            String expectedError = "User with id - " + id + " not found";

            lenient()
                    .doThrow(new NotFoundException(expectedError))
                    .when(repository)
                    .findById(id);

            doNothing()
                    .when(repository)
                    .delete(USER_TEST_BUILDER);

            Exception exception = assertThrows(NotFoundException.class,
                    () -> service.delete(USER_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).isEqualTo(expectedError);
        }

        @Test
        void deleteUserShouldReturnServerErrorException() {
            String expectedError = "Error with Delete user";

            Exception exception = assertThrows(ServerErrorException.class,
                    () -> service.delete(USER_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).contains(expectedError);
        }

    }

    @Nested
    class UpdateTest {

        @Test
        void updateUserShouldReturnUser() {
            User expected = USER_TEST_BUILDER;
            long id = expected.getId();

            doReturn(expected)
                    .when(repository)
                    .save(expected);

            doReturn(Optional.of(expected))
                    .when(repository)
                    .findById(id);

            User actual = service.update(expected);

            assertThat(actual).isEqualTo(expected);
        }


        @Test
        void updateUserShouldReturnExceptionNotFound() {
            long id = USER_TEST_BUILDER.getId();
            String expectedError = "User with id - " + id + " not found";

            lenient()
                    .doThrow(new NotFoundException(expectedError))
                    .when(repository)
                    .findById(id);

            doNothing()
                    .when(repository)
                    .save(USER_TEST_BUILDER);

            Exception exception = assertThrows(NotFoundException.class,
                    () -> service.update(USER_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).isEqualTo(expectedError);
        }

        @Test
        void updateUserShouldReturnServerErrorException() {
            String expectedError = "Error with Update user:";

            Exception exception = assertThrows(ServerErrorException.class,
                    () -> service.update(USER_TEST_BUILDER));
            String actualError = exception.getMessage();

            assertThat(actualError).contains(expectedError);
        }

    }

    @Nested
    class FindAllBy {

        @Test
        void getAllShouldReturnListOfSizeOne() {
            String findWord = "Vitaliy";
            int expectedSize = 1;
            List<User> list = List.of(USER_TEST_BUILDER);

            doReturn(list)
                    .when(repository)
                    .findAll();

            List<User> actualValues = service.findAllBy(findWord);

            assertThat(actualValues).hasSize(expectedSize);
        }

        @Test
        void testShouldReturnExceptionNotFound() {
            String expectedError = "User not found";

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
