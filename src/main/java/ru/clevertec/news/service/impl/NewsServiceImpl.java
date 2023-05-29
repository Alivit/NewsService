package ru.clevertec.news.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.news.dao.NewsRepository;
import ru.clevertec.news.entity.News;
import ru.clevertec.news.mapper.Mapper;
import ru.clevertec.news.service.NewsService;

import java.io.IOException;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    @Transactional
    public News create(News news) {
        newsRepository.save(news);

        return getById(news.getId());
    }

    @Override
    public Page<News> getAll(Pageable pageable) {
        Page<News> news = newsRepository.findAll(pageable);

        return news;
    }

    @Override
    @Transactional
    public News update(News news) {
        try {
            News newsUpdated = updateFieldsNews(news);
            newsRepository.save(newsUpdated);
            return newsUpdated;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public News deleteById(Long id) {
        News news = getById(id);
        newsRepository.delete(news);

        return news;
    }

    @Override
    public News getById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    private News updateFieldsNews(News news) throws JsonProcessingException {
        News newsUpdated = getById(news.getId());
        Map<String, Object> fields = Mapper.toMap(news);
        fields.forEach((key, value) -> {
            switch (key) {
                case "title" ->
                        newsUpdated.setTitle((String) value);
                case "text" ->
                        newsUpdated.setText((String) value);
                case "comments" ->
                        newsUpdated.setComments(new ObjectMapper().
                                convertValue(value, new TypeReference<>() {}));
            }
        });

        return newsUpdated;
    }

}
