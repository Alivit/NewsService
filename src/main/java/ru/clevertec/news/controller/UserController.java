package ru.clevertec.news.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.exception_handler.exception.NotFoundException;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.logging.aop.annotation.Logging;
import ru.clevertec.news.controller.openapi.UserOpenAPI;
import ru.clevertec.news.entity.User;
import ru.clevertec.news.service.UserService;

import java.util.List;

/**
 * The UserController class represents the controller for user-related operations.
 * It handles requests related to users and interacts with the UserService.
 */
@Logging
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController implements UserOpenAPI {

    private final UserService service;

    /**
     * Retrieves list of users with the specified word.
     *
     * @param pageable The parameter pageable contains filtering and sorting for a list of users.
     * @return The list of users with the specified pageable.
     * @throws NotFoundException If no users is found.
     */
    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        Page<User> response = service.getAll(pageable);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Retrieves a user with the specified ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object with the specified ID.
     * @throws NotFoundException If no user is found with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User response = service.getById(id);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Retrieves list of users with the specified word.
     *
     * @param word The word parameter for full-text search of users.
     * @return The list of users object with the specified ID.
     * @throws NotFoundException If no users is found.
     */
    @GetMapping("/find")
    public ResponseEntity<List<User>> findAllUserBy(@RequestParam String word){
        List<User> response = service.findAllBy(word);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Creates a new user with the specified details.
     *
     * @param user The User object containing the details of the user to be created.
     * @return The created User object.
     * @throws ServerErrorException If the provided user data is invalid.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User response = service.create(user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Update a new user with the specified details.
     *
     * @param user The User object containing the details of the user to be updated.
     * @return The updated User object.
     * @throws ServerErrorException If the provided user data is invalid.
     * @throws NotFoundException If the provided user not found in database.
     */
    @PatchMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User response = service.update(user);

        return ResponseEntity.ok(response);
    }

    /**
     * Delete user with the specified details.
     *
     * @param user The User object containing the details of the user to be deleted.
     * @return The deleted User object.
     * @throws ServerErrorException If the provided user data is invalid.
     * @throws NotFoundException If the provided user not found in database.
     */
    @DeleteMapping()
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        User response = service.delete(user);

        return ResponseEntity.ok(response);
    }

    /**
     * Banned a user with the specified ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object with the specified ID.
     * @throws NotFoundException If no user is found with the specified ID.
     */
    @PostMapping("/ban/{id}")
    public ResponseEntity<User> banUser(@PathVariable("id") Long id) {
        User response = service.ban(id);

        return ResponseEntity.ok(response);
    }
}
