package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.attendance.vo.FeedbackMissionAttendanceVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeFeedbackMissionAttendancesResponseDto(
        List<FeedbackMissionAttendanceVo> attendanceList
) {
    public static GetChallengeFeedbackMissionAttendancesResponseDto of(List<FeedbackMissionAttendanceVo> attendanceList) {
        return GetChallengeFeedbackMissionAttendancesResponseDto.builder()
                .attendanceList(attendanceList)
                .build();
    }
}
