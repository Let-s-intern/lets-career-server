package org.letscareer.letscareer.domain.pg.provider;

import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.type.RefundType;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.pg.type.CancelReason;
import org.springframework.stereotype.Service;

@Service
public interface PgProvider {
    TossPaymentsResponseDto requestPayments(String paymentKey, String orderId, String amount);

    TossPaymentsResponseDto requestPaymentDetail(String paymentKey);

    TossPaymentsResponseDto cancelPayments(RefundType refundType, String paymentKey, Integer cancelAmount, String cancelReason);
}
