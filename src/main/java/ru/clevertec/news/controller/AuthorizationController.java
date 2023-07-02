package ru.clevertec.news.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.logging.aop.annotation.Logging;
import ru.clevertec.news.controller.openapi.AuthorizationOpenAPI;
import ru.clevertec.news.entity.User;
import ru.clevertec.news.service.UserService;

/**
 * The AuthorizationController class represents the controller for user-related operations.
 * It handles requests related to comments and interacts with the UserService.
 */
@Logging
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor()
public class AuthorizationController implements AuthorizationOpenAPI {

    private final UserService service;

    /**
     * Registry a new user with the specified details.
     *
     * @param user The User object containing the details of the user to be registration.
     * @return The registration User object.
     * @throws ServerErrorException If the provided user data is invalid.
     */
    @PostMapping
    public ResponseEntity<User> registryUser(@RequestBody User user) {
        User response = service.create(user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
