package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;

@Builder(access = AccessLevel.PRIVATE)
public record CreditConfirmParameter(
        String name,
        String orderId,
        String programTitle,
        String amount
) {
    public static CreditConfirmParameter of(String name,
                                            String programTitle,
                                            TossPaymentsResponseDto responseDto) {
        return CreditConfirmParameter.builder()
                .name(name)
                .orderId(responseDto.orderId())
                .programTitle(programTitle)
                .amount(String.valueOf(responseDto.totalAmount()))
                .build();
    }
}
