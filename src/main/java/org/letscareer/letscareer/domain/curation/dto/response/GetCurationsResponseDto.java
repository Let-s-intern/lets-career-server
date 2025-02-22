package org.letscareer.letscareer.domain.curation.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetCurationsResponseDto(
        List<GetCurationResponseDto> curationList
) {
    public static GetCurationsResponseDto of(List<GetCurationResponseDto> curationList) {
        return GetCurationsResponseDto.builder()
                .curationList(curationList)
                .build();
    }
}
