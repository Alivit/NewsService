package ru.clevertec.news.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.clevertec.news.entity.listener.CommentListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(CommentListener.class)
@Table(name = "comments")
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class Comment {

    @Id
    @Positive(message = "Id must be greater than 0")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotBlank(message = "Username cannot be empty or null")
    private String username;

    @NotBlank(message = "Text cannot be empty or null")
    private String text;

    @Column(name = "create_date_comment")
    private LocalDateTime createDateComment;

    @Column(name = "update_date_comment")
    private LocalDateTime updateDateComment;

    @Valid
    @ManyToOne()
    @JoinColumn(name = "news_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private News news;
}
