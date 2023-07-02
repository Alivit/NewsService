package ru.clevertec.news.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.news.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
