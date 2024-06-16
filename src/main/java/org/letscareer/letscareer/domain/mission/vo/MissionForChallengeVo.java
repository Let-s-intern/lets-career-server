package org.letscareer.letscareer.domain.mission.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;
import org.letscareer.letscareer.domain.missiontemplate.type.MissionTemplateType;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MissionForChallengeVo(
        Long id,
        Integer th,
        MissionTemplateType missionType,
        MissionStatusType missionStatusType,
        Integer attendanceCount,
        Integer lateAttendanceCount,
        Integer score,
        Integer lateScore,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
