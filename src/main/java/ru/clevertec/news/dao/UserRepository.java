package ru.clevertec.news.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.news.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
