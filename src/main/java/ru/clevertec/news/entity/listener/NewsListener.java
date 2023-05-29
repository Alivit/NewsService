package ru.clevertec.news.entity.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import ru.clevertec.news.entity.News;

import java.time.LocalDateTime;

public class NewsListener {

    private LocalDateTime dateTime;

    @PrePersist
    private void createDate(News news){
        dateTime = LocalDateTime.now();
        news.setCreateDateNews(dateTime);
        news.setUpdateDateNews(dateTime);
    }

    @PreUpdate
    private void updateDate(News news){
        dateTime = LocalDateTime.now();
        news.setUpdateDateNews(dateTime);
    }
}
