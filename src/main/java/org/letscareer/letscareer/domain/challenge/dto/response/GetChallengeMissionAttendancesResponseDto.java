package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.attendance.vo.MissionAttendanceVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeMissionAttendancesResponseDto(
        List<MissionAttendanceVo> attendanceList
) {
    public static GetChallengeMissionAttendancesResponseDto of(List<MissionAttendanceVo> attendanceList) {
        return GetChallengeMissionAttendancesResponseDto.builder()
                .attendanceList(attendanceList)
                .build();
    }
}
