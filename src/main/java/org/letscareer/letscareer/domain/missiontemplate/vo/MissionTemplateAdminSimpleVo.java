package org.letscareer.letscareer.domain.missiontemplate.vo;

import lombok.Builder;

@Builder
public record MissionTemplateAdminSimpleVo(
        Long id,
        String title
) {
}
