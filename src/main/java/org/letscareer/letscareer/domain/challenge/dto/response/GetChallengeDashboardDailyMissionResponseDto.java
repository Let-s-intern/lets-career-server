package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceDailyMissionVo;
import org.letscareer.letscareer.domain.mission.vo.DailyMissionVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeDashboardDailyMissionResponseDto(
        DailyMissionVo dailyMission,
        AttendanceDailyMissionVo attendanceInfo
) {
    public static GetChallengeDashboardDailyMissionResponseDto of(DailyMissionVo dailyMission,
                                                                  AttendanceDailyMissionVo attendanceInfo) {
        return GetChallengeDashboardDailyMissionResponseDto.builder()
                .dailyMission(dailyMission)
                .attendanceInfo(attendanceInfo)
                .build();
    }
}
