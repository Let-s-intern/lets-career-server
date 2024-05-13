package org.letscareer.letscareer.domain.missiontemplate.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MissionTemplateAdminVo(
        Long id,
        LocalDateTime createDate,
        String title,
        String description,
        String guide,
        String templateLink
) {
}
