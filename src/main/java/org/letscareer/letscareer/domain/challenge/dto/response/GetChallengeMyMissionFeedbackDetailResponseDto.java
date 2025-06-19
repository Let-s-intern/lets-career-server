package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceFeedbackVo;
import org.letscareer.letscareer.domain.mission.vo.MyMissionFeedbackVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeMyMissionFeedbackDetailResponseDto(
        MyMissionFeedbackVo missionInfo,
        AttendanceFeedbackVo attendanceInfo
) {
    public static GetChallengeMyMissionFeedbackDetailResponseDto of(MyMissionFeedbackVo missionInfo, AttendanceFeedbackVo attendanceInfo) {
        return GetChallengeMyMissionFeedbackDetailResponseDto.builder()
                .missionInfo(missionInfo)
                .attendanceInfo(attendanceInfo)
                .build();
    }
}
