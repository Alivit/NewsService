package ru.clevertec.news.entity.listener;

import jakarta.persistence.PrePersist;
import ru.clevertec.news.entity.User;

import java.time.LocalDateTime;

public class UserListener {

    private LocalDateTime dateTime;

    @PrePersist
    private void registryDate(User user){
        dateTime = LocalDateTime.now();
        user.setDateOfRegistry(dateTime);
    }
}
