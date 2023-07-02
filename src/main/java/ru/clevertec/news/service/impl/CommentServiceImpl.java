package ru.clevertec.news.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.exception_handler.exception.NotFoundException;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.logging.aop.annotation.Logging;
import ru.clevertec.news.dao.CommentRepository;
import ru.clevertec.news.entity.Comment;
import ru.clevertec.news.service.CommentService;
import ru.clevertec.news.util.Mapper;

import java.util.List;
import java.util.Map;

@Logging
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    @CachePut(value = "comment", key = "#result.getId()")
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
    @CachePut(value = "comment", key = "#result.getId()")
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
    @CacheEvict(value = "comment", key = "#result.getId()")
    public Comment delete(Comment comment) {
        try {
            Comment deletedComment = getById(comment.getId());
            commentRepository.delete(deletedComment);

            return deletedComment;
        }catch (Exception e){
            throw new ServerErrorException("Error with Delete comment: " + e);
        }
    }

    @Override
    @Cacheable(value = "comment")
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Comment with id - " + id + " not found"));
    }

    @Override
    public List<Comment> findAllBy(String word) {
        List<Comment> comments = commentRepository.findAll();
        if(comments.isEmpty()) throw new NotFoundException("Comments not found");

        return comments.stream()
                .filter(obj -> obj.toStringForFind().contains(word))
                .toList();
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
