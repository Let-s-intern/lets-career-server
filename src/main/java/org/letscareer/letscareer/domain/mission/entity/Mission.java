package org.letscareer.letscareer.domain.mission.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;
import org.letscareer.letscareer.domain.mission.type.MissionType;
import org.letscareer.letscareer.domain.mission.type.converter.MissionStatusConverter;
import org.letscareer.letscareer.domain.mission.type.converter.MissionTypeConverter;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.letscareer.letscareer.domain.score.entity.MissionScore;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Convert(converter = MissionTypeConverter.class)
    private MissionType type;
    @NotNull
    @Convert(converter = MissionStatusConverter.class)
    @Builder.Default
    private MissionStatusType missionStatusType = MissionStatusType.WAITING;
    @NotNull
    @Builder.Default
    private Integer attendanceCount = 0;
    @NotNull
    @Builder.Default
    private Integer lateAttendanceCount = 0;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "missionEssential", fetch = FetchType.LAZY)
    private List<Contents> essentialContentsList;
    @OneToMany(mappedBy = "missionAdditional", fetch = FetchType.LAZY)
    private List<Contents> additionalContentsList;
    @OneToMany(mappedBy = "missionLimited", fetch = FetchType.LAZY)
    private List<Contents> limitedContentsList;
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Attendance> attendanceList = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_template_id")
    private MissionTemplate missionTemplate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_id")
    private MissionScore missionScore;

    public static Mission createMission(CreateMissionRequestDto createMissionRequestDto, Challenge challenge, MissionTemplate missionTemplate) {
        return Mission.builder()
                .th(createMissionRequestDto.th())
                .title(createMissionRequestDto.title())
                .type(createMissionRequestDto.type())
                .startDate(createMissionRequestDto.startDate().atTime(6, 0))
                .endDate(createMissionRequestDto.startDate().atTime(23, 59, 59))
                .challenge(challenge)
                .missionTemplate(missionTemplate)
                .build();
    }

    public void setMissionScore(MissionScore missionScore) {
        this.missionScore = missionScore;
    }

    public void setEssentialContentsList(List<Contents> contentsList) {
        this.essentialContentsList = contentsList;
    }

    public void setAdditionalContents(List<Contents> contentsList) {
        this.additionalContentsList = contentsList;
    }

    public void setLimitedContents(List<Contents> contentsList) {
        this.limitedContentsList = contentsList;
    }
}
