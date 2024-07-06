package org.letscareer.letscareer.domain.attendance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.attendance.dto.request.CreateAttendanceRequestDto;
import org.letscareer.letscareer.domain.attendance.dto.request.UpdateAttendanceRequestDto;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.attendance.type.converter.AttendanceResultConverter;
import org.letscareer.letscareer.domain.attendance.type.converter.AttendanceStatusConverter;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Attendance createAttendance(Mission mission,
                                              CreateAttendanceRequestDto createRequestDto,
                                              AttendanceStatus status,
                                              User user) {
        return Attendance.builder()
                .link(createRequestDto.link())
                .status(status)
                .mission(mission)
                .user(user)
                .build();
    }

    public void updateAttendanceAdmin(UpdateAttendanceRequestDto updateAttendanceRequestDto) {
        this.link = updateValue(this.link, updateAttendanceRequestDto.link());
        this.status = updateValue(this.status, updateAttendanceRequestDto.status());
        this.result = updateValue(this.result, updateAttendanceRequestDto.result());
        this.comments = updateValue(this.comments, updateAttendanceRequestDto.comments());
    }

    public void updateAttendanceLink(String link) {
        this.link = updateValue(this.link, link);
    }

    public void updateAttendanceStatus(AttendanceStatus status) {
        this.status = updateValue(this.status, status);
    }

    public void updateAttendanceResult(AttendanceResult result) {
        this.result = updateValue(this.result, result);
    }

}
