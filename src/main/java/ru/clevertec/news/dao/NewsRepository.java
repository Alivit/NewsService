package ru.clevertec.news.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.news.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {

}
