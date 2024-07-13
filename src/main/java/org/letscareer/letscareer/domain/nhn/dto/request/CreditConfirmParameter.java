package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CreditConfirmParameter(
        String 이름,
        String 주문번호,
        String 프로그램명,
        String 결제금액
) {
    public static CreditConfirmParameter of(String 이름,
                                            String 주문번호,
                                            String 프로그램명,
                                            String 결제금액) {
        return CreditConfirmParameter.builder()
                .이름(이름)
                .주문번호(주문번호)
                .프로그램명(프로그램명)
                .결제금액(결제금액)
                .build();
    }
}
