package org.letscareer.letscareer.domain.mission.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.contents.entity.Contents;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;
import org.letscareer.letscareer.domain.mission.type.MissionType;
import org.letscareer.letscareer.domain.mission.type.converter.MissionStatusConverter;
import org.letscareer.letscareer.domain.mission.type.converter.MissionTypeConverter;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
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
    private String title;
    @NotNull
    @Convert(converter = MissionTypeConverter.class)
    private MissionType type;
    @Convert(converter = MissionStatusConverter.class)
    private MissionStatusType missionStatusType;
    private Integer refund;
    private Integer attendanceCount;
    private Integer lateAttendanceCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "mission", fetch = FetchType.LAZY)
    private List<Contents> essentialContentsList;

    @OneToMany(mappedBy = "mission", fetch = FetchType.LAZY)
    private List<Contents> additionalContentsList;

    @OneToMany(mappedBy = "mission", fetch = FetchType.LAZY)
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

}
