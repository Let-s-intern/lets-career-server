package org.letscareer.letscareer.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;
import org.letscareer.letscareer.domain.mission.type.converter.MissionStatusConverter;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "attendance")
@Entity
public class Mission extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;
    @Convert(converter = MissionStatusConverter.class)
    private MissionStatusType missionStatusType;
    private Integer refund;
    private Integer attendanceCount;
    private Integer lateAttendanceCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String comment;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Attendance> attendanceList = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;
}
