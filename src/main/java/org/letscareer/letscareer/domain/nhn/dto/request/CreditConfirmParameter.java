package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;

@Builder(access = AccessLevel.PRIVATE)
public record CreditConfirmParameter(
        String 이름,
        String 주문번호,
        String 프로그램명,
        String 결제금액
) {
    public static CreditConfirmParameter of(String 이름,
                                            String 프로그램명,
                                            TossPaymentsResponseDto responseDto) {
        return CreditConfirmParameter.builder()
                .이름(이름)
                .주문번호(responseDto.paymentKey())
                .프로그램명(프로그램명)
                .결제금액(String.valueOf(responseDto.totalAmount()))
                .build();
    }
}
