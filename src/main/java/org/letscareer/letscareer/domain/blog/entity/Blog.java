package org.letscareer.letscareer.domain.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.blog.dto.request.CreateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.dto.request.UpdateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.letscareer.letscareer.domain.blog.type.converter.BlogTypeConverter;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;
import org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

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
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @Builder.Default
    private List<BlogHashTag> blogHashTags = new ArrayList<>();
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

    public void updateBlog(UpdateBlogRequestDto requestDto) {
        this.title = updateValue(this.title, requestDto.title());
        this.category = updateValue(this.category, requestDto.category());
        this.thumbnail = updateValue(this.thumbnail, requestDto.thumbnail());
        this.description = updateValue(this.description, requestDto.description());
        this.content = updateValue(this.content, requestDto.content());
        this.ctaLink = updateValue(this.ctaLink, requestDto.ctaLink());
        this.ctaText = updateValue(this.ctaText, requestDto.ctaText());
        this.displayDate = updateValue(this.displayDate, requestDto.displayDate());
    }

    public void addBlogHashTag(BlogHashTag blogHashTag) {
        this.blogHashTags.add(blogHashTag);
    }
}
