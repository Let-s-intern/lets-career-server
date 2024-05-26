package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetProgramsForDurationResponseDto(
        List<GetProgramForDurationResponseDto<?>> programList
) {
    public static GetProgramsForDurationResponseDto of(List<GetProgramForDurationResponseDto<?>> programList) {
        return GetProgramsForDurationResponseDto.builder()
                .programList(programList)
                .build();
    }
}
