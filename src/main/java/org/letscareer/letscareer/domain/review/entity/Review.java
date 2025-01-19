package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@Getter
@Entity
public abstract class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;
    private Integer score;
    private Integer npsScore;
    private String goodPoint;
    private String badPoint;
    @Builder.Default
    private Boolean isVisible = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReviewItem> reviewItemList = new ArrayList<>();
}
