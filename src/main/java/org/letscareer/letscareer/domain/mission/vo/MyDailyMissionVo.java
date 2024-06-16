package org.letscareer.letscareer.domain.mission.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;
import org.letscareer.letscareer.domain.mission.type.MissionType;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MyDailyMissionVo(
        Long id,
        Integer th,
        String title,
        MissionType type,
        LocalDateTime startDate,
        LocalDateTime endDate,
        List<ContentsMissionVo> essentialContentsList,
        List<ContentsMissionVo> additionalContentsList,
        MissionStatusType status,
        String missionTag,
        String description,
        String guide,
        String templateLink
) {
    public MyDailyMissionVo(Mission mission, String missionTag, String description, String guide, String templateLink) {
        this(
                mission.getId(),
                mission.getTh(),
                mission.getTitle(),
                mission.getType(),
                mission.getStartDate(),
                mission.getEndDate(),
                mission.getEssentialContentsList().stream().map(missionContents -> ContentsMissionVo.of(missionContents.getContents())).toList(),
                mission.getAdditionalContentsList().stream().map(missionContents -> ContentsMissionVo.of(missionContents.getContents())).toList(),
                mission.getMissionStatusType(),
                missionTag,
                description,
                guide,
                templateLink
        );
    }
}
