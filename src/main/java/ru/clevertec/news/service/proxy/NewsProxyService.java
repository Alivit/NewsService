package ru.clevertec.news.service.proxy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.news.entity.News;
import ru.clevertec.news.service.NewsService;

public class NewsProxyService implements NewsService {

    @Override
    public News create(News news) {
        return null;
    }

    @Override
    public Page<News> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public News update(News news) {
        return null;
    }

    @Override
    public News deleteById(Long id) {
        return null;
    }

    @Override
    public News getById(Long id) {
        return null;
    }
}
