package ru.clevertec.news.builder.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import ru.clevertec.news.builder.TestBuilder;
import ru.clevertec.news.entity.Comment;
import ru.clevertec.news.entity.News;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(staticName = "aNews")
@With
public class NewsTestBuilder implements TestBuilder<News> {

    private Long id = 1L;
    private String username = "Vitaliy";
    private String title = "Jeff Bezos Steps Down as Amazon CEO";
    private String text ="In February 2021, Amazon founder Jeff Bezos announced that he would be" +
            " stepping down as CEO of the company later in the year. " +
            "He will be replaced by Andy Jassy, who currently leads Amazon Web Services.";
    private LocalDateTime createDateNews = LocalDateTime.now();
    private LocalDateTime updateDateNews = createDateNews;
    private List<Comment> comments = List.of(
            CommentTestBuilder.aComment().build(),
            new Comment(2L, "Vasya", "Cool", LocalDateTime.now(), LocalDateTime.now(), null),
            new Comment(3L, "Alla", "Yay", LocalDateTime.now(), LocalDateTime.now(), null)
    );

    @Override
    public News build() {
        return News.builder()
                .id(id)
                .username(username)
                .title(title)
                .text(text)
                .createDateNews(createDateNews)
                .updateDateNews(updateDateNews)
                .comments(comments)
                .build();
    }
}
