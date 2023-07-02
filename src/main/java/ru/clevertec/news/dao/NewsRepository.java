package ru.clevertec.news.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.news.entity.News;

import java.time.LocalDateTime;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

}
