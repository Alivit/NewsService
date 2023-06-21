package ru.clevertec.news.service.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.clevertec.logging.aop.annotation.Logging;
import ru.clevertec.news.caches.CacheProvider;
import ru.clevertec.news.caches.LFU;
import ru.clevertec.news.caches.LRU;
import ru.clevertec.news.config.CacheConfig;
import ru.clevertec.news.entity.News;
import ru.clevertec.news.service.NewsService;

@Logging
@Service
@RequiredArgsConstructor
public class NewsProxy implements NewsService {

    @Qualifier("newsServiceImpl")
    private final NewsService service;

    private CacheProvider<Object, News> cache;

    {
        CacheConfig config = new CacheConfig();
        if("LRU".equalsIgnoreCase(config.getAlgorithm())){
            cache =  new LRU<>(config.getCapacity());
        }
        else {
            cache =  new LFU<>(config.getCapacity());
        }
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
    public News deleteById(Long id) {
        News response = service.deleteById(id);

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
}
