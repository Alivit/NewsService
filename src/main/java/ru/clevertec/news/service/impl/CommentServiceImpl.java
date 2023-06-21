package ru.clevertec.news.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.logging.aop.annotation.Logging;
import ru.clevertec.news.dao.CommentRepository;
import ru.clevertec.news.entity.Comment;
import ru.clevertec.exception_handler.exception.NotFoundException;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.news.mapper.Mapper;
import ru.clevertec.news.service.CommentService;

import java.util.Map;

@Logging
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public Comment create(Comment comment) {
        try {
            commentRepository.save(comment);

            return getById(comment.getId());
        }catch (Exception e){
            throw new ServerErrorException("Error with Insert comment: " + e);
        }
    }

    @Override
    public Page<Comment> getAll(Pageable pageable) {
        Page<Comment> comments = commentRepository.findAll(pageable);
        if(comments.isEmpty()) throw new NotFoundException("Comments not found");

        return comments;
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        try {
            Comment commentUpdated = updateFieldsComment(comment);
            commentRepository.save(commentUpdated);

            return commentUpdated;
        } catch (Exception e){
            throw new ServerErrorException("Error with Update comment: " + e);
        }
    }

    @Override
    @Transactional
    public Comment deleteById(Long id) {
        try {
            Comment comment = getById(id);
            commentRepository.delete(comment);

            return comment;
        }catch (Exception e){
            throw new ServerErrorException("Error with Delete comment: " + e);
        }
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Comment with id - " + id + " not found"));
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
