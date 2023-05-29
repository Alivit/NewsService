package ru.clevertec.news.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.news.entity.News;
import ru.clevertec.news.service.NewsService;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<Page<News>> getComments(Pageable pageable) {
        Page<News> response = newsService.getAll(pageable);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getCommentById(@PathVariable Long id){
        News response = newsService.getById(id);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<News> createCertificate(@RequestBody News news) {
        News response = newsService.create(news);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<News> updateCertificate(@RequestBody News news){
        News response = newsService.update(news);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<News> deleteCertificate(@PathVariable Long id) {
        News response = newsService.deleteById(id);

        return ResponseEntity.ok(response);
    }

}
