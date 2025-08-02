package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.type.converter.ProgramTypeConverter;
import org.letscareer.letscareer.domain.review.dto.request.CreateBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.vo.BlogReviewOpenGraphVo;

import java.time.LocalDateTime;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;

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

    public static BlogReview createBonusBlogReview(CreateBlogReviewRequestDto requestDto, BlogReviewOpenGraphVo openGraphVo, Attendance attendance) {
        return BlogReview.builder()
                .programType(requestDto.programType())
                .programTitle(requestDto.programTitle())
                .name(requestDto.name())
                .title(openGraphVo.title())
                .description(openGraphVo.description())
                .url(openGraphVo.url())
                .postDate(requestDto.postDate())
                .attendance(attendance)
                .isVisible(false)
                .build();
    }

    public void updateThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void updateBlogReview(UpdateBlogReviewRequestDto requestDto) {
        this.postDate = updateValue(this.postDate, requestDto.postDate());
        this.programType = updateValue(this.programType, requestDto.programType());
        this.programTitle = updateValue(this.programTitle, requestDto.programTitle());
        this.name = updateValue(this.name, requestDto.name());
        this.isVisible = updateValue(this.isVisible, requestDto.isVisible());
    }

    public void updateBlogReviewByUrl(BlogReviewOpenGraphVo openGraphVo, String thumbnail) {
        this.title = updateValue(this.title, openGraphVo.title());
        this.description = updateValue(this.description, openGraphVo.description());
        this.url = updateValue(this.url, openGraphVo.url());
        this.thumbnail = updateValue(this.thumbnail, thumbnail);
    }

    public void approveForBonus() {
        this.isVisible = true;
    }
}
