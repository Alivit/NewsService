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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.exception_handler.exception.NotFoundException;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.logging.aop.annotation.Logging;
import ru.clevertec.news.controller.openapi.NewsOpenAPI;
import ru.clevertec.news.entity.News;
import ru.clevertec.news.service.NewsService;

import java.util.List;

/**
 * The NewsController class represents the controller for user-related operations.
 * It handles requests related to news and interacts with the NewsService.
 */
@Logging
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('JOURNALIST')")
public class NewsController implements NewsOpenAPI {

    @Qualifier(value = "newsProxy")
    private final NewsService newsService;

    /**
     * Retrieves list of news with the specified word.
     *
     * @param pageable The parameter pageable contains filtering and sorting for a list of news.
     * @return The list of news with the specified pageable.
     * @throws NotFoundException If no news is found.
     */
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Page<News>> getAllNews(Pageable pageable) {
        Page<News> response = newsService.getAll(pageable);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Retrieves news with the specified ID.
     *
     * @param id The ID of the news to retrieve.
     * @return The News object with the specified ID.
     * @throws NotFoundException If no news is found with the specified ID.
     */
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<News> getNewsById(@PathVariable Long id){
        News response = newsService.getById(id);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Retrieves list of news with the specified word.
     *
     * @param word The word parameter for full-text search of news.
     * @return The list of news object with the specified ID.
     * @throws NotFoundException If no news is found.
     */
    @GetMapping("/find")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<News>> findAllNewsBy(@RequestParam String word){
        List<News> response = newsService.findAllBy(word);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Creates new news with the specified details.
     *
     * @param news The News object containing the details of the news to be created.
     * @return The created News object.
     * @throws ServerErrorException If the provided news data is invalid.
     */
    @PostMapping
    @PreAuthorize("#news.username == authentication.principal.username || hasAuthority('ADMIN')")
    public ResponseEntity<News> createNews(@RequestBody News news) {
        News response = newsService.create(news);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Update new news with the specified details.
     *
     * @param news The News object containing the details of the news to be updated.
     * @return The updated News object.
     * @throws ServerErrorException If the provided news data is invalid.
     * @throws NotFoundException If the provided news not found in database.
     */
    @PatchMapping()
    @PreAuthorize("#news.username == authentication.principal.username || hasAuthority('ADMIN')")
    public ResponseEntity<News> updateNews(@RequestBody News news){
        News response = newsService.update(news);

        return ResponseEntity.ok(response);
    }

    /**
     * Delete news with the specified details.
     *
     * @param news The News object containing the details of the news to be deleted.
     * @return The deleted News object.
     * @throws ServerErrorException If the provided news data is invalid.
     * @throws NotFoundException If the provided news not found in database.
     */
    @DeleteMapping()
    @PreAuthorize("#news.username == authentication.principal.username || hasAuthority('ADMIN')")
    public ResponseEntity<News> deleteNews(@RequestBody News news) {
        News response = newsService.delete(news);

        return ResponseEntity.ok(response);
    }

}
