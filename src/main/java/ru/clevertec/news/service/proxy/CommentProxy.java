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
    public Comment deleteById(Long id) {
        Comment response = service.deleteById(id);

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
}
