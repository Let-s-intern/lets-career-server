package org.letscareer.letscareer.domain.nhn.dto.request.credit;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.global.common.utils.string.StringUtils;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;

@Builder(access = AccessLevel.PRIVATE)
public record CreditConfirmParameter(
        String name,
        String orderId,
        String programTitle,
        String amount
) {
    public static CreditConfirmParameter of(String name,
                                            String programTitle,
                                            CreatePaymentRequestDto paymentInfo) {
        return CreditConfirmParameter.builder()
                .name(name)
                .orderId(paymentInfo.orderId())
                .programTitle(programTitle)
                .amount(StringUtils.withThousandsSeparator(paymentInfo.amount()))
                .build();
    }
}
