package org.letscareer.letscareer.domain.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.blog.dto.request.CreateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.letscareer.letscareer.domain.blog.type.converter.BlogTypeConverter;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;
import org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "blog")
@Entity
public class Blog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id;
    private String title;
    @Convert(converter = BlogTypeConverter.class)
    private BlogType category;
    private String thumbnail;
    private String description;
    private String content;
    private String ctaLink;
    private String ctaText;
    private LocalDateTime displayDate;
    private LocalDateTime deleteDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_hash_tag_id")
    private BlogHashTag blogHashTag;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_rating_id")
    private BlogRating blogRating;
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @Builder.Default
    private List<BlogRating> blogRatingList = new ArrayList<>();

    public static Blog createBlog(CreateBlogRequestDto requestDto) {
        return Blog.builder()
                .title(requestDto.title())
                .category(requestDto.category())
                .thumbnail(requestDto.thumbnail())
                .description(requestDto.description())
                .content(requestDto.content())
                .ctaLink(requestDto.ctaLink())
                .ctaText(requestDto.ctaText())
                .displayDate(requestDto.displayDate())
                .build();
    }
}
