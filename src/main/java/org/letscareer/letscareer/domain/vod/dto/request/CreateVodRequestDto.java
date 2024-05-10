package org.letscareer.letscareer.domain.vod.dto.request;

import org.letscareer.letscareer.domain.classification.dto.request.CreateVodClassificationRequestDto;

import java.util.List;

public record CreateVodRequestDto(
        String title,
        String shortDesc,
        String thumbnail,
        String job,
        List<CreateVodClassificationRequestDto> programTypeInfo
) {
}
