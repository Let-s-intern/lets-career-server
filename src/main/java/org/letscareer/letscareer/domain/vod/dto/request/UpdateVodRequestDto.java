package org.letscareer.letscareer.domain.vod.dto.request;

import org.letscareer.letscareer.domain.admincalssification.request.CreateVodAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.dto.request.CreateVodClassificationRequestDto;

import java.util.List;

public record UpdateVodRequestDto(
        String title,
        String shortDesc,
        String thumbnail,
        String job,
        String link,
        Boolean isVisible,
        List<CreateVodClassificationRequestDto> programTypeInfo,
        List<CreateVodAdminClassificationRequestDto> adminProgramTypeInfo
) {
}
