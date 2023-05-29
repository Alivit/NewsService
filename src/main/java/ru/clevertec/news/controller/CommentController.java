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
import ru.clevertec.news.entity.Comment;
import ru.clevertec.news.service.CommentService;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Page<Comment>> getComments(Pageable pageable) {
        Page<Comment> response = commentService.getAll(pageable);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id){
        Comment response = commentService.getById(id);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Comment> createCertificate(@RequestBody Comment comment) {
        Comment response = commentService.create(comment);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<Comment> updateCertificate(@RequestBody Comment comment){
        Comment response = commentService.update(comment);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteCertificate(@PathVariable Long id) {
        Comment response = commentService.deleteById(id);

        return ResponseEntity.ok(response);
    }

}
