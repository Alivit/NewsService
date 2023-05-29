package ru.clevertec.news.entity.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import ru.clevertec.news.entity.Comment;

import java.time.LocalDateTime;

public class CommentListener {

    private LocalDateTime dateTime = LocalDateTime.now();

    @PrePersist
    private void createDate(Comment comment){
        comment.setCreateDateComment(dateTime);
        comment.setUpdateDateComment(dateTime);
    }

    @PreUpdate
    private void updateDate(Comment comment){
        comment.setUpdateDateComment(dateTime);
    }
}
