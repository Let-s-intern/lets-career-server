package org.letscareer.letscareer.domain.challenge.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.challenge.type.converter.ChallengeTypeConverter;
import org.letscareer.letscareer.domain.classification.entity.ChallengeClassification;
import org.letscareer.letscareer.domain.faq.entity.FaqChallenge;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.letscareer.letscareer.domain.review.entity.ChallengeReview;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "challenge")
@Entity
public class Challenge extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long id;
    private String title;
    private String shortDesc;
    private String description;
    @Builder.Default
    private Integer currentCount = 0;
    private Integer participationCount;
    private String thumbnail;
    private String chatLink;
    private String chatPassword;
    private String zoomLink;
    private String zoomPassword;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime deadline;
    @Builder.Default
    private Boolean isVisible = false;
    @Convert(converter = ChallengeTypeConverter.class)
    private ChallengeType challengeType;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChallengeClassification> classificationList = new ArrayList<>();
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChallengePrice> priceList = new ArrayList<>();
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<FaqChallenge> faqList = new ArrayList<>();
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChallengeReview> noticeList = new ArrayList<>();
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    public static Challenge createChallenge(CreateChallengeRequestDto requestDto) {
        return Challenge.builder()
                .title(requestDto.title())
                .shortDesc(requestDto.shortDesc())
                .description(requestDto.desc())
                .participationCount(requestDto.participationCount())
                .thumbnail(requestDto.thumbnail())
                .startDate(requestDto.startDate())
                .endDate(requestDto.endDate())
                .deadline(requestDto.deadline())
                .chatLink(requestDto.chatLink())
                .chatPassword(requestDto.chatPassword())
                .challengeType(requestDto.challengeType())
                .build();
    }

    public void addChallengeClassificationList(ChallengeClassification challengeClassification) {
        this.classificationList.add(challengeClassification);
    }

    public void addChallengePriceList(ChallengePrice challengePrice) {
        this.priceList.add(challengePrice);
    }

    public void addChallengeFaqList(FaqChallenge faqChallenge) {
        this.faqList.add(faqChallenge);
    }
}
