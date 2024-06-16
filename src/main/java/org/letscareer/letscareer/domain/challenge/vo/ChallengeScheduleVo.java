package org.letscareer.letscareer.domain.challenge.vo;

import org.letscareer.letscareer.domain.attendance.vo.AttendanceDailyMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MissionScheduleVo;

public record ChallengeScheduleVo(
        MissionScheduleVo missionInfo,
        AttendanceDailyMissionVo attendanceInfo
) {
}
