package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.type.RefundType;

@Builder(access = AccessLevel.PRIVATE)
public record CreditRefundParameter(
        String name,
        String orderId,
        String programTitle,
        String orderStatus,
        Integer paymentPrice,
        Integer refundPrice
) {
    public static CreditRefundParameter of(String name, String orderId, String programTitle, RefundType refundType, Integer paymentPrice, Integer refundPrice) {
        return CreditRefundParameter.builder()
                .name(name)
                .orderId(orderId)
                .programTitle(programTitle)
                .orderStatus(refundType.equals(RefundType.ALL) ? "전체취소" : "부분취소")
                .paymentPrice(paymentPrice)
                .refundPrice(refundPrice)
                .build();
    }
}
