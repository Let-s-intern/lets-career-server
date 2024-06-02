package org.letscareer.letscareer.domain.live.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.classification.entity.LiveClassification;
import org.letscareer.letscareer.domain.faq.entity.FaqLive;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.dto.request.UpdateLiveRequestDto;
import org.letscareer.letscareer.domain.live.type.ProgressType;
import org.letscareer.letscareer.domain.live.type.converter.ProgressTypeConverter;
import org.letscareer.letscareer.domain.price.entity.LivePrice;
import org.letscareer.letscareer.domain.program.dto.response.ZoomMeetingResponseDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.EntityUpdateValueUtils.updateValue;

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
    private String description;
    @Builder.Default
    private Integer currentCount = 0;
    private Integer participationCount;
    private String thumbnail;
    private String mentorName;
    private String job;
    private String place;
    private String zoomLink;
    private String zoomPassword;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime beginning;
    private LocalDateTime deadline;
    @Builder.Default
    private Boolean isVisible = false;
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

    public static Live createLive(CreateLiveRequestDto requestDto, ZoomMeetingResponseDto zoomMeetingInfo) {
        return Live.builder()
                .title(requestDto.title())
                .shortDesc(requestDto.shortDesc())
                .description(requestDto.desc())
                .participationCount(requestDto.participationCount())
                .thumbnail(requestDto.thumbnail())
                .mentorName(requestDto.mentorName())
                .job(requestDto.job())
                .place(requestDto.place())
                .startDate(requestDto.startDate())
                .endDate(requestDto.endDate())
                .beginning(requestDto.beginning())
                .deadline(requestDto.deadline())
                .progressType(requestDto.progressType())
                .zoomLink(zoomMeetingInfo.join_url())
                .zoomPassword(zoomMeetingInfo.password())
                .build();
    }

    public void updateLive(UpdateLiveRequestDto requestDto) {
        this.title = updateValue(this.title, requestDto.title());
        this.shortDesc = updateValue(this.shortDesc, requestDto.shortDesc());
        this.description = updateValue(this.description, requestDto.desc());
        this.participationCount = updateValue(this.participationCount, requestDto.participationCount());
        this.thumbnail = updateValue(this.thumbnail, requestDto.thumbnail());
        this.mentorName = updateValue(this.mentorName, requestDto.mentorName());
        this.job = updateValue(this.job, requestDto.job());
        this.place = updateValue(this.place, requestDto.place());
        this.startDate = updateValue(this.startDate, requestDto.startDate());
        this.endDate = updateValue(this.endDate, requestDto.endDate());
        this.beginning = updateValue(this.beginning, requestDto.beginning());
        this.deadline = updateValue(this.deadline, requestDto.deadline());
        this.progressType = updateValue(this.progressType, requestDto.progressType());
        this.isVisible = updateValue(this.isVisible, requestDto.isVisible());
    }


    public void addLiveClassification(LiveClassification liveClassification) {
        this.classificationList.add(liveClassification);
    }

    public void addFaqLiveList(FaqLive faqLive) {
        this.faqList.add(faqLive);
    }

    public void setInitClassificationList() {
        this.classificationList = new ArrayList<>();
    }

    public void setInitPriceList() {
        this.priceList = new ArrayList<>();
    }

    public void setInitFaqList() {
        this.faqList = new ArrayList<>();
    }
}
