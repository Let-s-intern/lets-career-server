package org.letscareer.letscareer.domain.attendance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.attendance.dto.request.AttendanceCreateRequestDto;
import org.letscareer.letscareer.domain.attendance.dto.request.AttendanceUpdateRequestDto;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.attendance.type.converter.AttendanceResultConverter;
import org.letscareer.letscareer.domain.attendance.type.converter.AttendanceStatusConverter;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.score.entity.AttendanceScore;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;

import static org.letscareer.letscareer.global.common.utils.EntityUpdateValueUtils.updateValue;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "attendance")
@Entity
public class Attendance extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;
    private String link;
    @Convert(converter = AttendanceStatusConverter.class)
    private AttendanceStatus status;
    @Builder.Default
    @Convert(converter = AttendanceResultConverter.class)
    private AttendanceResult result = AttendanceResult.WAITING;
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mission_id")
    private Mission mission;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "score_id")
    private AttendanceScore attendanceScore;

    public static Attendance createAttendance(Mission mission,
                                              AttendanceCreateRequestDto createRequestDto,
                                              AttendanceStatus status,
                                              User user) {
        return Attendance.builder()
                .link(createRequestDto.link())
                .status(status)
                .mission(mission)
                .user(user)
                .build();
    }

    public void updateAttendanceAdmin(AttendanceUpdateRequestDto attendanceUpdateRequestDto) {
        this.link = updateValue(this.link, attendanceUpdateRequestDto.link());
        this.status = updateValue(this.status, attendanceUpdateRequestDto.status());
        this.result = updateValue(this.result, attendanceUpdateRequestDto.result());
        this.comments = updateValue(this.comments, attendanceUpdateRequestDto.comments());
    }

    public void updateAttendance(AttendanceUpdateRequestDto attendanceUpdateRequestDto) {
        this.link = updateValue(this.link, attendanceUpdateRequestDto.link());
    }

    public void setAttendanceScore(AttendanceScore attendanceScore) {
        this.attendanceScore = attendanceScore;
    }
}
