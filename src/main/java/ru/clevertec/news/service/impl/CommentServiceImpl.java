package ru.clevertec.news.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.news.dao.CommentRepository;
import ru.clevertec.news.entity.Comment;
import ru.clevertec.news.mapper.Mapper;
import ru.clevertec.news.service.CommentService;

import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public Comment create(Comment comment) {
        commentRepository.save(comment);

        return getById(comment.getId());
    }

    @Override
    public Page<Comment> getAll(Pageable pageable) {
        Page<Comment> comments = commentRepository.findAll(pageable);

        return comments;
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        try {
            Comment commentUpdated = updateFieldsComment(comment);
            commentRepository.save(commentUpdated);
            return commentUpdated;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Comment deleteById(Long id) {
        Comment comment = getById(id);
        commentRepository.delete(comment);

        return comment;
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    private Comment updateFieldsComment(Comment comment) throws JsonProcessingException {
        Comment commentUpdated = getById(comment.getId());
        Map<String, Object> fields = Mapper.toMap(commentUpdated);
        fields.forEach((key, value) -> {
            switch (key) {
                case "username" ->
                        commentUpdated.setUsername((String) value);
                case "text" ->
                        commentUpdated.setText((String) value);
                case "news" ->
                        commentUpdated.setNews(new ObjectMapper().
                                convertValue(value, new TypeReference<>() {}));
            }
        });

        return comment;
    }

}
