package ru.clevertec.users.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.exception_handler.exception.NotFoundException;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.logging.aop.annotation.Logging;
import ru.clevertec.users.dao.UserRepository;
import ru.clevertec.users.entity.User;
import ru.clevertec.users.service.UserService;
import ru.clevertec.users.util.Mapper;

import java.util.Map;

@Logging
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User create(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(user);

            return getById(user.getId());
        }catch (Exception e){
            throw new ServerErrorException("Error with Insert user: " + e);
        }
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        Page<User> user = repository.findAll(pageable);
        if(user.isEmpty()) throw new NotFoundException("Users not found");

        return user;
    }

    @Override
    @Transactional
    public User update(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User userUpdated = updateFieldsNews(user);
            repository.save(userUpdated);

            return userUpdated;
        } catch (Exception e){
            throw new ServerErrorException("Error with Update user: " + e);
        }
    }

    @Override
    @Transactional
    public User deleteById(Long id) {
        try {
            User user = getById(id);
            repository.delete(user);

            return user;
        } catch (Exception e){
            throw new ServerErrorException("Error with Delete user: " + e);
        }
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id - " + id + " not found"));
    }



    private User updateFieldsNews(User user) throws JsonProcessingException {
        User userUpdated = getById(user.getId());
        Map<String, Object> fields = Mapper.toMap(user);
        fields.forEach((key, value) -> {
            switch (key) {
                case "username" ->
                        userUpdated.setUsername((String) value);
                case "password" ->
                        userUpdated.setPassword((String) value);
                case "active" ->
                        userUpdated.setActive((Boolean) value);
                case "roles" ->
                        userUpdated.setRoles(new ObjectMapper().
                                convertValue(value, new TypeReference<>() {}));
            }
        });

        return userUpdated;
    }

    public User ban(Long id) {
        try {
            User user = getById(id);
            user.setActive(!user.isActive());
            repository.save(user);

            return getById(id);
        }catch (Exception e){
            throw new ServerErrorException("Error with Ban user: " + e);
        }
    }

}
