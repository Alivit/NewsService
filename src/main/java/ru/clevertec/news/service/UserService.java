package ru.clevertec.news.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.exception_handler.exception.NotFoundException;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.news.entity.News;
import ru.clevertec.news.entity.User;

import java.util.List;

/**
 * The UserService interface represents the service for user-related operations.
 * It provides methods to interact with the underlying data layer and perform CRUD operations on users.
 */
public interface UserService {

    /**
     * Creates a new user with the specified details.
     *
     * @param user The User object containing the details of the user to be created.
     * @return The created User object.
     */
    public User create(@Valid User user);

    /**
     * Retrieves list of users with the specified word.
     *
     * @param pageable The parameter pageable contains filtering and sorting for a list of users.
     * @return The list of users with the specified pageable.
     */
    public Page<User> getAll(Pageable pageable);

    /**
     * Update a new user with the specified details.
     *
     * @param user The User object containing the details of the user to be updated.
     * @return The updated User object.
     */
    public User update(@Valid User user);

    /**
     * Delete user with the specified details.
     *
     * @param user The User object containing the details of the user to be deleted.
     * @return The deleted User object.
     */
    public User delete(User user);

    /**
     * Retrieves a user with the specified ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object with the specified ID.
     */
    public User getById(@Positive Long id);

    /**
     * Retrieves list of users with the specified word.
     *
     * @param word The word parameter for full-text search of users.
     * @return The list of users object with the specified ID.
     */
    public List<User> findAllBy(@NotBlank String word);

    /**
     * Banned a user with the specified ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object with the specified ID.
     */
    public User ban(Long id);
}
