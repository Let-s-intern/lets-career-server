package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.type.converter.ProgramTypeConverter;
import org.letscareer.letscareer.domain.review.dto.request.CreateBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.vo.BlogReviewOpenGraphVo;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class BlogReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_review_id")
    private Long id;

    @Convert(converter = ProgramTypeConverter.class)
    private ProgramType programType;

    private String programTitle;

    private String name;

    private String title;

    private String description;

    private String url;

    private String thumbnail;

    private LocalDateTime postDate;

    @Builder.Default
    @NotNull
    private Boolean isVisible = false;

    public static BlogReview createBlogReview(CreateBlogReviewRequestDto requestDto, BlogReviewOpenGraphVo openGraphVo) {
        return BlogReview.builder()
                .programType(requestDto.programType())
                .programTitle(requestDto.programTitle())
                .name(requestDto.name())
                .title(openGraphVo.title())
                .description(openGraphVo.description())
                .url(openGraphVo.url())
                .postDate(requestDto.postDate())
                .build();
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
