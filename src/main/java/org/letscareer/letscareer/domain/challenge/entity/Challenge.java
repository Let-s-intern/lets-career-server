package org.letscareer.letscareer.domain.challenge.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.admincalssification.entity.ChallengeAdminClassification;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.request.UpdateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.challenge.type.converter.ChallengeTypeConverter;
import org.letscareer.letscareer.domain.challengeguide.entity.ChallengeGuide;
import org.letscareer.letscareer.domain.challlengenotice.entity.ChallengeNotice;
import org.letscareer.letscareer.domain.classification.entity.ChallengeClassification;
import org.letscareer.letscareer.domain.faq.entity.FaqChallenge;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.letscareer.letscareer.domain.program.dto.response.ZoomMeetingResponseDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

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
    private String criticalNotice;
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
    private LocalDateTime beginning;
    private LocalDateTime deadline;
    @Builder.Default
    private Boolean isVisible = false;
    @Convert(converter = ChallengeTypeConverter.class)
    private ChallengeType challengeType;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChallengeApplication> applicationList = new ArrayList<>();
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChallengeClassification> classificationList = new ArrayList<>();
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChallengeAdminClassification> adminClassificationList = new ArrayList<>();
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChallengePrice> priceList = new ArrayList<>();
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<FaqChallenge> faqList = new ArrayList<>();
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChallengeNotice> challengeNoticeList = new ArrayList<>();
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChallengeGuide> challengeGuideList = new ArrayList<>();

    public static Challenge createChallenge(CreateChallengeRequestDto requestDto, ZoomMeetingResponseDto zoomMeetingInfo) {
        return Challenge.builder()
                .title(requestDto.title())
                .shortDesc(requestDto.shortDesc())
                .description(requestDto.desc())
                .criticalNotice(requestDto.criticalNotice())
                .participationCount(requestDto.participationCount())
                .thumbnail(requestDto.thumbnail())
                .startDate(requestDto.startDate())
                .endDate(requestDto.endDate())
                .beginning(requestDto.beginning())
                .deadline(requestDto.deadline())
                .chatLink(requestDto.chatLink())
                .chatPassword(requestDto.chatPassword())
                .challengeType(requestDto.challengeType())
                .zoomLink(zoomMeetingInfo.join_url())
                .zoomPassword(zoomMeetingInfo.password())
                .build();
    }

    public void updateChallenge(UpdateChallengeRequestDto requestDto) {
        this.title = updateValue(this.title, requestDto.title());
        this.shortDesc = updateValue(this.shortDesc, requestDto.shortDesc());
        this.description = updateValue(this.description, requestDto.desc());
        this.criticalNotice = updateValue(this.criticalNotice, requestDto.criticalNotice());
        this.participationCount = updateValue(this.participationCount, requestDto.participationCount());
        this.thumbnail = updateValue(this.thumbnail, requestDto.thumbnail());
        this.startDate = updateValue(this.startDate, requestDto.startDate());
        this.endDate = updateValue(this.endDate, requestDto.endDate());
        this.beginning = updateValue(this.beginning, requestDto.beginning());
        this.deadline = updateValue(this.deadline, requestDto.deadline());
        this.chatLink = updateValue(this.chatLink, requestDto.chatLink());
        this.chatPassword = updateValue(this.chatPassword, requestDto.chatPassword());
        this.challengeType = updateValue(this.challengeType, requestDto.challengeType());
        this.isVisible = updateValue(this.isVisible, requestDto.isVisible());
    }

    public void addChallengeClassification(ChallengeClassification challengeClassification) {
        this.classificationList.add(challengeClassification);
    }

    public void addChallengeAdminClassification(ChallengeAdminClassification challengeAdminClassification) {
        this.adminClassificationList.add(challengeAdminClassification);
    }

    public void addChallengeApplicationList(ChallengeApplication challengeApplication) {
        this.applicationList.add(challengeApplication);
    }

    public void addChallengePriceList(ChallengePrice challengePrice) {
        this.priceList.add(challengePrice);
    }

    public void addChallengeFaqList(FaqChallenge faqChallenge) {
        this.faqList.add(faqChallenge);
    }

    public void setInitClassificationList() {
        this.classificationList = new ArrayList<>();
    }

    public void setInitAdminClassificationList() {
        this.adminClassificationList = new ArrayList<>();
    }

    public void setInitPriceList() {
        this.priceList = new ArrayList<>();
    }

    public void setInitFaqList() {
        this.faqList = new ArrayList<>();
    }


}
