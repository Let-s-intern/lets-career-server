package org.letscareer.letscareer.domain.mission.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;
import org.letscareer.letscareer.domain.mission.type.MissionType;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MissionAdminVo(
        MissionType type,
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer refund,
        List<ContentsMissionVo> essentialContentsList,
        List<ContentsMissionVo> additionalContentsList,
        List<ContentsMissionVo> limitedContentsList
) {
}
