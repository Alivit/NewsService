package ru.clevertec.news.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.exception_handler.exception.NotFoundException;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.logging.aop.annotation.Logging;
import ru.clevertec.news.dao.UserRepository;
import ru.clevertec.news.entity.User;
import ru.clevertec.news.util.Mapper;
import ru.clevertec.news.service.UserService;

import java.util.List;
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
    @CachePut(value = "user", key = "#result.getId()")
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
    @CachePut(value = "user", key = "#result.getId()")
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
    @CacheEvict(value = "user", key = "#result.getId()")
    public User delete(User user) {
        try {
            User deletedUser = getById(user.getId());
            repository.delete(deletedUser);

            return deletedUser;
        } catch (Exception e){
            throw new ServerErrorException("Error with Delete user: " + e);
        }
    }

    @Override
    @Cacheable(value = "user")
    public User getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id - " + id + " not found"));
    }

    @Override
    public List<User> findAllBy(String word) {
        List<User> user = repository.findAll();
        if(user.isEmpty()) throw new NotFoundException("News not found");

        return user.stream()
                .filter(obj -> obj.toStringForFind().contains(word))
                .toList();
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
