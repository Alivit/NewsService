package ru.clevertec.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
