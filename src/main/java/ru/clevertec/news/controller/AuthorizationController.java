package ru.clevertec.news.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.logging.aop.annotation.Logging;
import ru.clevertec.news.entity.User;
import ru.clevertec.news.service.UserService;

@Logging
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor()
public class AuthorizationController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<User> registryUser(@RequestBody User user) {
        User response = service.create(user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
