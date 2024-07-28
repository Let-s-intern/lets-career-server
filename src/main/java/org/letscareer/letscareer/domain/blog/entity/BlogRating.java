package org.letscareer.letscareer.domain.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.blog.dto.request.CreateRatingRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "blog_rating")
@Entity
public class BlogRating extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_rating_id")
    private Long id;
    private String title;
    private String content;
    @Builder.Default
    private Integer score = 0;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    public static BlogRating createBlogRating(Blog blog, CreateRatingRequestDto requestDto) {
        return BlogRating.builder()
                .title(requestDto.title())
                .score(requestDto.score())
                .blog(blog)
                .build();
    }
}
