package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceDailyMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MyDailyMissionVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeMyDailyMissionResponseDto(
        MyDailyMissionVo dailyMission,
        AttendanceDailyMissionVo attendanceInfo
) {
    public static GetChallengeMyDailyMissionResponseDto of(MyDailyMissionVo dailyMission,
                                                           AttendanceDailyMissionVo attendanceInfo) {
        return GetChallengeMyDailyMissionResponseDto.builder()
                .dailyMission(dailyMission)
                .attendanceInfo(attendanceInfo)
                .build();
    }
}
