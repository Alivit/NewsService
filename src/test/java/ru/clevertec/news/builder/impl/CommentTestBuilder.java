package ru.clevertec.news.builder.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import ru.clevertec.news.builder.TestBuilder;
import ru.clevertec.news.entity.Comment;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aComment")
@With
public class CommentTestBuilder implements TestBuilder<Comment> {

    private Long id = 1L;
    private String username = "Alivit";
    private String text = "Hello world";
    private LocalDateTime createDateNews = LocalDateTime.now();
    private LocalDateTime updateDateNews = createDateNews;

    @Override
    public Comment build() {
        return Comment.builder()
                .id(id)
                .username(username)
                .text(text)
                .createDateComment(createDateNews)
                .updateDateComment(updateDateNews)
                .build();
    }
}
