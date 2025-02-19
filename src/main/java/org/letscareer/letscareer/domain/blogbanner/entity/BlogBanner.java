package org.letscareer.letscareer.domain.blogbanner.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

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

    private String url;

    @Builder.Default
    private Boolean isVisible = false;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String file;

    @Builder.Default
    private Integer weight = 1;
}
