package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportApplicationMessageResponseDto(
        String message
) {
    public static GetReportApplicationMessageResponseDto of(String message) {
        return GetReportApplicationMessageResponseDto.builder()
                .message(message)
                .build();
    }
}
