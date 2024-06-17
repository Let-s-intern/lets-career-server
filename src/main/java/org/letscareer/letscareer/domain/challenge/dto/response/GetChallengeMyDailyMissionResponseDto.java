package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceDashboardVo;
import org.letscareer.letscareer.domain.mission.vo.MyDailyMissionVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeMyDailyMissionResponseDto(
        MyDailyMissionVo dailyMission,
        AttendanceDashboardVo attendanceInfo
) {
    public static GetChallengeMyDailyMissionResponseDto of(MyDailyMissionVo dailyMission,
                                                           AttendanceDashboardVo attendanceInfo) {
        return GetChallengeMyDailyMissionResponseDto.builder()
                .dailyMission(dailyMission)
                .attendanceInfo(attendanceInfo)
                .build();
    }
}
