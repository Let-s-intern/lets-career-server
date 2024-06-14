package org.letscareer.letscareer.domain.score.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("attendance_score")
@Getter
@Entity
public class AttendanceScore extends Score {
    private Integer score = 0;
    private Integer adminScore = 0;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;

    @Builder(access = AccessLevel.PRIVATE)
    public AttendanceScore(Integer score, Attendance attendance) {
        super();
        this.score = score;
        this.adminScore = 0;
        this.attendance = attendance;
    }

    public static AttendanceScore creatAttendanceScore(Integer score, Attendance attendance) {
        AttendanceScore attendanceScore = AttendanceScore.builder()
                .score(score)
                .attendance(attendance)
                .build();
        attendance.setAttendanceScore(attendanceScore);
        return attendanceScore;
    }
}
