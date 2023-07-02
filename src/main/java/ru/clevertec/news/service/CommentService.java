package ru.clevertec.news.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.exception_handler.exception.NotFoundException;
import ru.clevertec.exception_handler.exception.ServerErrorException;
import ru.clevertec.news.entity.Comment;
import ru.clevertec.news.entity.News;

import java.util.List;

/**
 * The CommentService interface represents the service for comment-related operations.
 * It provides methods to interact with the underlying data layer and perform CRUD operations on comments.
 */
public interface CommentService {

    /**
     * Creates a new comment with the specified details.
     *
     * @param comment The Comment object containing the details of the comment to be created.
     * @return The created Comment object.
     */
    public Comment create(@Valid Comment comment);

    /**
     * Retrieves list of comments with the specified word.
     *
     * @param pageable The parameter pageable contains filtering and sorting for a list of comments.
     * @return The list of comments with the specified pageable.
     */
    public Page<Comment> getAll(Pageable pageable);

    /**
     * Update a new comment with the specified details.
     *
     * @param comment The Comment object containing the details of the comment to be updated.
     * @return The updated Comment object.
     */
    public Comment update(@Valid Comment comment);

    /**
     * Delete comment with the specified details.
     *
     * @param comment The Comment object containing the details of the comment to be deleted.
     * @return The deleted Comment object.
     */
    public Comment delete(Comment comment);

    /**
     * Retrieves a comment with the specified ID.
     *
     * @param id The ID of the comment to retrieve.
     * @return The Comment object with the specified ID.
     */
    public Comment getById(@Positive Long id);

    /**
     * Retrieves list of comments with the specified word.
     *
     * @param word The word parameter for full-text search of comments.
     * @return The list of comments object with the specified ID.
     */
    public List<Comment> findAllBy(@NotBlank String word);

}
