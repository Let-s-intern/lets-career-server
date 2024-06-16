package org.letscareer.letscareer.domain.mission.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.mission.type.MissionType;

import java.time.LocalDate;
import java.util.List;

public record CreateMissionRequestDto(
        @NotNull Integer th,
        @NotEmpty String title,
        @NotNull MissionType type,
        @Nullable Integer refund,
        @Nullable Integer score,
        @Nullable Integer lateScore,
        @NotNull LocalDate startDate,
        @NotNull Long missionTemplateId,
        List<Long> essentialContentsIdList,
        List<Long> additionalContentsIdList
) {
}
