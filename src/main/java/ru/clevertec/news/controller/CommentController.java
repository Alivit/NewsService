package ru.clevertec.news.controller;

import jakarta.annotation.security.PermitAll;
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
import ru.clevertec.news.controller.openapi.CommentOpenAPI;
import ru.clevertec.news.entity.Comment;
import ru.clevertec.news.service.CommentService;

@Logging
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor()
@PreAuthorize("hasAnyAuthority('JOURNALIST', 'SUBSCRIBER')")
public class CommentController implements CommentOpenAPI {

    @Qualifier(value = "commentProxy")
    private final CommentService commentService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Page<Comment>> getAllComments(Pageable pageable) {
        Page<Comment> response = commentService.getAll(pageable);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PermitAll
    @PreAuthorize("permitAll()")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id){
        Comment response = commentService.getById(id);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PostMapping
    @PreAuthorize("#comment.username == authentication.principal.username || hasAuthority('ADMIN')")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment response = commentService.create(comment);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping()
    @PreAuthorize("#comment.username == authentication.principal.username || hasAuthority('ADMIN')")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment){
        Comment response = commentService.update(comment);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#comment.username == authentication.principal.username || hasAuthority('ADMIN')")
    public ResponseEntity<Comment> deleteComment(@RequestBody Comment comment) {
        Comment response = commentService.delete(comment);

        return ResponseEntity.ok(response);
    }

}
