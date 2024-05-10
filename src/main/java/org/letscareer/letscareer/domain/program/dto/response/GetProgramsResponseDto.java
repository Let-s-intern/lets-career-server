package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetProgramsResponseDto(
        List<GetProgramResponseDto> programList
) {
    public static GetProgramsResponseDto of(List<GetProgramResponseDto> responseDtoList) {
        return GetProgramsResponseDto.builder()
                .programList(responseDtoList)
                .build();
    }
}
