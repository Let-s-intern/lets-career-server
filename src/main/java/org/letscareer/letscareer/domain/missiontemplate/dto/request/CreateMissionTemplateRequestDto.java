package org.letscareer.letscareer.domain.missiontemplate.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.missiontemplate.type.MissionTemplateType;

public record CreateMissionTemplateRequestDto(
        @NotNull MissionTemplateType type,
        @NotEmpty String title,
        @NotEmpty String description,
        @NotEmpty String guide,
        @NotEmpty String templateLink
) {
}
