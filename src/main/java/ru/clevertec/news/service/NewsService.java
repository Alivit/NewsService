package ru.clevertec.news.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.news.entity.News;

public interface NewsService {

    public News create(@Valid News news);

    public Page<News> getAll(Pageable pageable);

    public News update(@Valid News news);

    public News deleteById(@Positive Long id);

    public News getById(@Positive Long id);

}
