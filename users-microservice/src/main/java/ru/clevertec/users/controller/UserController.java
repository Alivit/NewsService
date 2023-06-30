package ru.clevertec.users.controller;

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
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.logging.aop.annotation.Logging;
import ru.clevertec.users.entity.User;
import ru.clevertec.users.service.UserService;

@Logging
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        Page<User> response = service.getAll(pageable);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User response = service.getById(id);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User response = service.create(user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User response = service.update(user);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User response = service.deleteById(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/ban/{id}")
    public ResponseEntity<User> banUser(@PathVariable("id") Long id) {
        User response = service.ban(id);

        return ResponseEntity.ok(response);
    }
}
