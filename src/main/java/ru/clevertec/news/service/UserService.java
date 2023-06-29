package ru.clevertec.news.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.news.entity.User;

public interface UserService {

    public User create(@Valid User user);

    public Page<User> getAll(Pageable pageable);

    public User update(@Valid User user);

    public User deleteById(@Positive Long id);

    public User getById(@Positive Long id);

    public User ban(Long id);
}
