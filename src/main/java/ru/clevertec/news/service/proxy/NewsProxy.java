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
import ru.clevertec.news.entity.News;
import ru.clevertec.news.service.NewsService;

import java.util.List;

/**
 * The NewsProxy class represents the service for news-related operations.
 * It provides methods to interact with the cache layer and perform CRUD operations on news.
 */
@Logging
@Service
@RequiredArgsConstructor
public class NewsProxy implements NewsService {

    @Qualifier("newsServiceImpl")
    private final NewsService service;

    private final CacheConfig<Long, News> config;
    private CacheProvider<Long, News> cache;

    @PostConstruct
    void init(){
        cache = config.getCache();
    }

    @Override
    public News create(News news) {
        News response = service.create(news);

        cache.put(response.getId(),response);

        return response;
    }

    @Override
    public Page<News> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @Override
    public News update(News news) {
        News response = service.update(news);

        if(cache.containsValue(news)){
            cache.delete(news.getId());
            cache.put(response.getId(),response);
        }

        return response;
    }

    @Override
    public News delete(News news) {
        News response = service.delete(news);

        if (cache.containsValue(response)) {
            cache.delete(response.getId());
        }

        return response;
    }

    @Override
    public News getById(Long id) {
        if (cache.containsKey(id)){
            return cache.get(id);
        }
        else {
            News response = service.getById(id);
            cache.put(response.getId(), response);

            return response;
        }
    }

    @Override
    public List<News> findAllBy(String word) {
        return service.findAllBy(word);
    }
}
