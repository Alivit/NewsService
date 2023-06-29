package ru.clevertec.news.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.logging.aop.annotation.Logging;
import ru.clevertec.news.controller.openapi.NewsOpenAPI;
import ru.clevertec.news.entity.News;
import ru.clevertec.news.service.NewsService;

@Logging
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('JOURNALIST')")
public class NewsController implements NewsOpenAPI {

    @Qualifier(value = "newsProxy")
    private final NewsService newsService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Page<News>> getAllNews(Pageable pageable) {
        Page<News> response = newsService.getAll(pageable);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<News> getNewsById(@PathVariable Long id){
        News response = newsService.getById(id);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PostMapping
    @PreAuthorize("#news.username == authentication.principal.username || hasAuthority('ADMIN')")
    public ResponseEntity<News> createNews(@RequestBody News news) {
        News response = newsService.create(news);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping()
    @PreAuthorize("#news.username == authentication.principal.username || hasAuthority('ADMIN')")
    public ResponseEntity<News> updateNews(@RequestBody News news){
        News response = newsService.update(news);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#news.username == authentication.principal.username || hasAuthority('ADMIN')")
    public ResponseEntity<News> deleteNews(@RequestBody News news) {
        News response = newsService.delete(news);

        return ResponseEntity.ok(response);
    }

}
