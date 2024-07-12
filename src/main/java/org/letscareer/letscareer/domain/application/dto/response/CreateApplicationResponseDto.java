package org.letscareer.letscareer.domain.application.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;

@Builder(access = AccessLevel.PRIVATE)
public record CreateApplicationResponseDto(
        TossPaymentsResponseDto tossInfo
) {
    public static CreateApplicationResponseDto of(TossPaymentsResponseDto responseDto) {
        return CreateApplicationResponseDto.builder()
                .tossInfo(responseDto)
                .build();
    }
}