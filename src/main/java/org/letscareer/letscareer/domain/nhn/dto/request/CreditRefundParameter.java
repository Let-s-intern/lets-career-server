package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.global.common.utils.string.StringUtils;
import org.letscareer.letscareer.domain.payment.type.RefundType;

@Builder(access = AccessLevel.PRIVATE)
public record CreditRefundParameter(
        String name,
        String orderId,
        String programTitle,
        String orderStatus,
        String paymentPrice,
        String refundPrice
) {
    public static CreditRefundParameter of(String name, String orderId, String programTitle, RefundType refundType, Integer paymentPrice, Integer refundPrice) {
        return CreditRefundParameter.builder()
                .name(name)
                .orderId(orderId)
                .programTitle(programTitle)
                .orderStatus(refundType.equals(RefundType.ALL) ? "전체취소" : "부분취소")
                .paymentPrice(StringUtils.toStringWithThousandsSeparator(paymentPrice))
                .refundPrice(StringUtils.toStringWithThousandsSeparator(refundPrice))
                .build();
    }
}
