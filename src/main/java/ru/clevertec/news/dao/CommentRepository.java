package ru.clevertec.news.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.news.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
