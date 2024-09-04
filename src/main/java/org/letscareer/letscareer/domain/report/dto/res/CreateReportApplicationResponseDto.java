package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;

@Builder(access = AccessLevel.PRIVATE)
public record CreateReportApplicationResponseDto(
        TossPaymentsResponseDto tossInfo
) {
    public static CreateReportApplicationResponseDto of(TossPaymentsResponseDto tossInfo) {
        return CreateReportApplicationResponseDto.builder()
                .tossInfo(tossInfo)
                .build();
    }
}
