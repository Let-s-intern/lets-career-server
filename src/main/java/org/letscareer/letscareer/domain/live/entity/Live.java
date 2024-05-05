package org.letscareer.letscareer.domain.live.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.classification.entity.LiveClassification;
import org.letscareer.letscareer.domain.faq.entity.FaqLive;
import org.letscareer.letscareer.domain.live.type.ProgressType;
import org.letscareer.letscareer.domain.live.type.converter.ProgressTypeConverter;
import org.letscareer.letscareer.domain.review.entity.LiveReview;
import org.letscareer.letscareer.domain.price.entity.LivePrice;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "live")
@Entity
public class Live extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "live_id")
    private Long id;
    private String title;
    private String shortDesc;
    private String desc;
    private Integer participationCount;
    private String thumbnail;
    private String mentorName;
    private String stringJob;
    private String place;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime deadline;
    @Convert(converter = ProgressTypeConverter.class)
    private ProgressType progressType;

    @OneToMany(mappedBy = "live", cascade = CascadeType.ALL)
    @Builder.Default
    private List<LiveClassification> classificationList = new ArrayList<>();
    @OneToMany(mappedBy = "live", cascade = CascadeType.ALL)
    @Builder.Default
    private List<LivePrice> priceList = new ArrayList<>();
    @OneToMany(mappedBy = "live", cascade = CascadeType.ALL)
    @Builder.Default
    private List<FaqLive> faqList = new ArrayList<>();
    @OneToMany(mappedBy = "live", cascade = CascadeType.ALL)
    @Builder.Default
    private List<LiveReview> reviewList = new ArrayList<>();
}
