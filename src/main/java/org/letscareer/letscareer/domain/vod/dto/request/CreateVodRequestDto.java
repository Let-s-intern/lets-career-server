package org.letscareer.letscareer.domain.vod.dto.request;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.admincalssification.request.CreateVodAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.dto.request.CreateVodClassificationRequestDto;

import java.util.List;

public record CreateVodRequestDto(
        @NotNull
        String title,
        String shortDesc,
        @NotNull
        String thumbnail,
        String job,
        @NotNull
        String link,
        @NotNull
        List<CreateVodClassificationRequestDto> programTypeInfo,
        @NotNull
        List<CreateVodAdminClassificationRequestDto> adminProgramTypeInfo
) {
}
