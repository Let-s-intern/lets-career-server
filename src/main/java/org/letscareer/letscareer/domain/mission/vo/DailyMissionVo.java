package org.letscareer.letscareer.domain.mission.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;
import org.letscareer.letscareer.domain.mission.type.MissionType;
import org.letscareer.letscareer.domain.missioncontents.entity.MissionContents;
import org.letscareer.letscareer.domain.missiontemplate.type.MissionTemplateType;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record DailyMissionVo(
        Long id,
        Integer th,
        String title,
        MissionType type,
        LocalDateTime startDate,
        LocalDateTime endDate,
//        List<MissionContents> essentialContentsList,
//        List<MissionContents> additionalContentsList,
        MissionStatusType status,
        MissionTemplateType missionTemplateType,
        String description,
        String guide,
        String templateLink
) {
}
