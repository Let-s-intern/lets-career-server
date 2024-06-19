package org.letscareer.letscareer.domain.mission.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CreateMissionRequestDto(
        @NotNull Integer th,
        @NotEmpty String title,
        @Nullable Integer score,
        @Nullable Integer lateScore,
        @NotNull LocalDateTime startDate,
        @NotNull LocalDateTime endDate,
        @NotNull Long missionTemplateId,
        List<Long> essentialContentsIdList,
        List<Long> additionalContentsIdList
) {
}
