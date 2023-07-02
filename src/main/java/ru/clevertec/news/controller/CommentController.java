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
import ru.clevertec.news.controller.openapi.CommentOpenAPI;
import ru.clevertec.news.entity.Comment;
import ru.clevertec.news.service.CommentService;

import java.util.List;

/**
 * The CommentController class represents the controller for comment-related operations.
 * It handles requests related to comments and interacts with the CommentService.
 */
@Logging
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor()
@PreAuthorize("hasAnyAuthority('JOURNALIST', 'SUBSCRIBER')")
public class CommentController implements CommentOpenAPI {

    @Qualifier(value = "commentProxy")
    private final CommentService commentService;

    /**
     * Retrieves list of comments with the specified word.
     *
     * @param pageable The parameter pageable contains filtering and sorting for a list of comments.
     * @return The list of comments with the specified pageable.
     * @throws NotFoundException If no comments is found.
     */
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Page<Comment>> getAllComments(Pageable pageable) {
        Page<Comment> response = commentService.getAll(pageable);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Retrieves a comment with the specified ID.
     *
     * @param id The ID of the comment to retrieve.
     * @return The Comment object with the specified ID.
     * @throws NotFoundException If no comment is found with the specified ID.
     */
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id){
        Comment response = commentService.getById(id);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Retrieves list of comments with the specified word.
     *
     * @param word The word parameter for full-text search of comments.
     * @return The list of comments object with the specified ID.
     * @throws NotFoundException If no comments is found.
     */
    @GetMapping("/find")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Comment>> findAllCommentsBy(@RequestParam String word){
        List<Comment> response = commentService.findAllBy(word);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * Creates a new comment with the specified details.
     *
     * @param comment The Comment object containing the details of the comment to be created.
     * @return The created Comment object.
     * @throws ServerErrorException If the provided comment data is invalid.
     */
    @PostMapping
    @PreAuthorize("#comment.username == authentication.principal.username || hasAuthority('ADMIN')")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment response = commentService.create(comment);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Update a new comment with the specified details.
     *
     * @param comment The Comment object containing the details of the comment to be updated.
     * @return The updated Comment object.
     * @throws ServerErrorException If the provided comment data is invalid.
     * @throws NotFoundException If the provided comment not found in database.
     */
    @PatchMapping()
    @PreAuthorize("#comment.username == authentication.principal.username || hasAuthority('ADMIN')")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment){
        Comment response = commentService.update(comment);

        return ResponseEntity.ok(response);
    }

    /**
     * Delete comment with the specified details.
     *
     * @param comment The Comment object containing the details of the comment to be deleted.
     * @return The deleted Comment object.
     * @throws ServerErrorException If the provided comment data is invalid.
     * @throws NotFoundException If the provided comment not found in database.
     */
    @DeleteMapping()
    @PreAuthorize("#comment.username == authentication.principal.username || hasAuthority('ADMIN')")
    public ResponseEntity<Comment> deleteComment(@RequestBody Comment comment) {
        Comment response = commentService.delete(comment);

        return ResponseEntity.ok(response);
    }

}
