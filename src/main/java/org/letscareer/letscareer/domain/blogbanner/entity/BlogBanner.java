package org.letscareer.letscareer.domain.blogbanner.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.dto.request.UpdateBlogBannerRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "blog_banner")
public class BlogBanner extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_banner_id")
    private Long id;

    private String title;

    private String link;

    @Builder.Default
    private Boolean isVisible = false;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String file;

    private Integer weight;

    public static BlogBanner createBlogBanner(CreateBlogBannerRequestDto requestDto){
        return BlogBanner.builder()
                .title(requestDto.title())
                .link(requestDto.link())
                .startDate(requestDto.startDate())
                .endDate(requestDto.endDate())
                .file(requestDto.file())
                .weight(requestDto.weight())
                .build();
    }

    public void updateBlogBanner(UpdateBlogBannerRequestDto requestDto){
        this.title = updateValue(this.title, requestDto.title());
        this.link = updateValue(this.link, requestDto.link());
        this.isVisible = updateValue(this.isVisible, requestDto.isVisible());
        this.startDate = updateValue(this.startDate, requestDto.startDate());
        this.endDate = updateValue(this.endDate, requestDto.endDate());
        this.file = updateValue(this.file, requestDto.file());
        this.weight = updateValue(this.weight, requestDto.weight());
    }
}
