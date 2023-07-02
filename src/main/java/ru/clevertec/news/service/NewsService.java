package ru.clevertec.news.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.exception_handler.exception.NotFoundException;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.news.entity.News;

import java.util.List;

/**
 * The NewsService interface represents the service for news-related operations.
 * It provides methods to interact with the underlying data layer and perform CRUD operations on news.
 */
public interface NewsService {

    /**
     * Creates new news with the specified details.
     *
     * @param news The News object containing the details of the news to be created.
     * @return The created News object.
     */
    public News create(@Valid News news);

    /**
     * Retrieves list of news with the specified word.
     *
     * @param pageable The parameter pageable contains filtering and sorting for a list of news.
     * @return The list of news with the specified pageable.
     */
    public Page<News> getAll(Pageable pageable);

    /**
     * Update new news with the specified details.
     *
     * @param news The News object containing the details of the news to be updated.
     * @return The updated News object.
     */
    public News update(@Valid News news);

    /**
     * Delete news with the specified details.
     *
     * @param news The News object containing the details of the news to be deleted.
     * @return The deleted News object.
     */
    public News delete(News news);

    /**
     * Retrieves news with the specified ID.
     *
     * @param id The ID of the news to retrieve.
     * @return The News object with the specified ID.
     */
    public News getById(@Positive Long id);

    /**
     * Retrieves list of news with the specified word.
     *
     * @param word The word parameter for full-text search of news.
     * @return The list of news object with the specified ID.
     */
    public List<News> findAllBy(@NotBlank String word);

}
