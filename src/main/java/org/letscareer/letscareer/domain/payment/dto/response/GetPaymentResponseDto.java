package org.letscareer.letscareer.domain.payment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;

@Builder(access = AccessLevel.PRIVATE)
public record GetPaymentResponseDto(
        GetPaymentProgramResponseDto programInfo,
        TossPaymentsResponseDto tossInfo
) {
    public static GetPaymentResponseDto of(GetPaymentProgramResponseDto programInfo,
                                           TossPaymentsResponseDto tossInfo) {
        return GetPaymentResponseDto.builder()
                .programInfo(programInfo)
                .tossInfo(tossInfo)
                .build();
    }
}
