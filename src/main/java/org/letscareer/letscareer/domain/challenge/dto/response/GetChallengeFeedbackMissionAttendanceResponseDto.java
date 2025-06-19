package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.attendance.vo.FeedbackMissionAttendanceDetailVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeFeedbackMissionAttendanceResponseDto(
        FeedbackMissionAttendanceDetailVo attendanceDetailVo
) {
    public static GetChallengeFeedbackMissionAttendanceResponseDto of(FeedbackMissionAttendanceDetailVo attendanceDetailVo) {
        return GetChallengeFeedbackMissionAttendanceResponseDto.builder()
                .attendanceDetailVo(attendanceDetailVo)
                .build();
    }
}
