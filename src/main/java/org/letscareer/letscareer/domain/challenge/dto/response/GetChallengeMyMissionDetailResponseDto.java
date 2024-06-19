package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceDashboardVo;
import org.letscareer.letscareer.domain.mission.vo.MyDailyMissionVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeMyMissionDetailResponseDto(
        MyDailyMissionVo missionInfo,
        AttendanceDashboardVo attendanceInfo
) {
    public static GetChallengeMyMissionDetailResponseDto of(MyDailyMissionVo missionInfo, AttendanceDashboardVo attendanceInfo) {
        return GetChallengeMyMissionDetailResponseDto.builder()
                .missionInfo(missionInfo)
                .attendanceInfo(attendanceInfo)
                .build();
    }
}
