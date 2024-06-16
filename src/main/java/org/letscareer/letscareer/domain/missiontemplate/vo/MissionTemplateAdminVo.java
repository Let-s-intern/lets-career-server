package org.letscareer.letscareer.domain.missiontemplate.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.missiontemplate.type.MissionTemplateType;

import java.time.LocalDateTime;

@Builder
public record MissionTemplateAdminVo(
        Long id,
        LocalDateTime createDate,
        String missionTag,
        String title,
        String description,
        String guide,
        String templateLink
) {
}
