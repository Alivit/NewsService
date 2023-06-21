package ru.clevertec.news.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.news.entity.Comment;

public interface CommentService {

    public Comment create(@Valid Comment comment);

    public Page<Comment> getAll(Pageable pageable);

    public Comment update(@Valid Comment comment);

    public Comment deleteById(@Positive Long id);

    public Comment getById(@Positive Long id);

}
