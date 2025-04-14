package org.letscareer.letscareer.domain.mission.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.request.UpdateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;
import org.letscareer.letscareer.domain.mission.type.converter.MissionStatusConverter;
import org.letscareer.letscareer.domain.missioncontents.entity.MissionContents;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.letscareer.letscareer.domain.score.entity.MissionScore;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
@Entity
public class Mission extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;
    @NotNull
    private Integer th;
    @NotNull
    private String title;
    @NotNull
    @Convert(converter = MissionStatusConverter.class)
    @Builder.Default
    private MissionStatusType missionStatusType = MissionStatusType.WAITING;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MissionContents> essentialContentsList = new ArrayList<>();
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MissionContents> additionalContentsList = new ArrayList<>();
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Attendance> attendanceList = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_template_id")
    private MissionTemplate missionTemplate;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "score_id")
    private MissionScore missionScore;

    public static Mission createMission(CreateMissionRequestDto createMissionRequestDto, Challenge challenge, MissionTemplate missionTemplate) {
        return Mission.builder()
                .th(createMissionRequestDto.th())
                .title(missionTemplate.getTitle())
                .startDate(createMissionRequestDto.startDate())
                .endDate(createMissionRequestDto.endDate())
                .challenge(challenge)
                .missionTemplate(missionTemplate)
                .build();
    }

    public static Mission copyMission(Challenge challenge, Mission mission, long dayDifference) {
        return Mission.builder()
                .th(mission.getTh())
                .title(mission.getTitle())
                .startDate(mission.getStartDate().plusDays(dayDifference))
                .endDate(mission.getEndDate().plusDays(dayDifference))
                .challenge(challenge)
                .missionTemplate(mission.missionTemplate)
                .build();
    }

    public void updateMission(UpdateMissionRequestDto requestDto) {
        this.th = updateValue(this.th, requestDto.th());
        this.title = updateValue(this.title, requestDto.title());
        this.startDate = updateValue(this.startDate, requestDto.startDate());
        this.endDate = updateValue(this.endDate, requestDto.endDate());
        this.missionStatusType = updateValue(this.missionStatusType, requestDto.status());
    }

    public void setMissionScore(MissionScore missionScore) {
        this.missionScore = missionScore;
    }

    public void setEssentialContentsList(List<MissionContents> missionContentsList) {
        this.essentialContentsList = missionContentsList;
    }

    public void setAdditionalContentsList(List<MissionContents> missionContentsList) {
        this.additionalContentsList = missionContentsList;
    }

    public void setInitMissionContentsList(ContentsType contentsType) {
        switch (contentsType) {
            case ESSENTIAL -> this.essentialContentsList = new ArrayList<>();
            case ADDITIONAL -> this.additionalContentsList = new ArrayList<>();
        }
    }

    public void updateMissionTemplate(MissionTemplate missionTemplate) {
        this.missionTemplate = updateValue(this.missionTemplate, missionTemplate);
    }
}
