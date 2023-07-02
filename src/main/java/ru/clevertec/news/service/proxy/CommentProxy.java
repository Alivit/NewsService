package ru.clevertec.news.service.proxy;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.clevertec.logging.aop.annotation.Logging;
import ru.clevertec.news.caches.CacheProvider;
import ru.clevertec.news.config.CacheConfig;
import ru.clevertec.news.entity.Comment;
import ru.clevertec.news.service.CommentService;

import java.util.List;

/**
 * The CommentProxy class represents the service for comment-related operations.
 * It provides methods to interact with the cache layer and perform CRUD operations on comments.
 */
@Logging
@Service
@RequiredArgsConstructor
public class CommentProxy implements CommentService {

    @Qualifier("commentServiceImpl")
    private final CommentService service;
    private final CacheConfig<Long, Comment> config;
    private CacheProvider<Long, Comment> cache;

    @PostConstruct
    void init(){
        cache = config.getCache();
    }

    @Override
    public Comment create(Comment comment) {
        Comment response = service.create(comment);

        cache.put(response.getId(),response);

        return response;
    }

    @Override
    public Page<Comment> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @Override
    public Comment update(Comment comment) {
        Comment response = service.update(comment);

        if(cache.containsValue(comment)){
            cache.delete(comment.getId());
            cache.put(response.getId(),response);
        }

        return response;
    }

    @Override
    public Comment delete(Comment comment) {
        Comment response = service.delete(comment);

        if (cache.containsValue(response)) {
            cache.delete(response.getId());
        }

        return response;
    }

    @Override
    public Comment getById(Long id) {
        if (!cache.containsKey(id)){
            Comment response = service.getById(id);
            cache.put(response.getId(), response);
        }

        return cache.get(id);
    }

    @Override
    public List<Comment> findAllBy(String word) {
        return service.findAllBy(word);
    }
}
