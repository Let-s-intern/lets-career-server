package org.letscareer.letscareer.domain.pg.provider;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.type.RefundType;
import org.letscareer.letscareer.domain.pg.dto.request.TossPaymentsCancelRequestDto;
import org.letscareer.letscareer.domain.pg.dto.request.TossPaymentsRequestDto;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.global.common.utils.toss.TossFeignController;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("TOSS")
public class TossProvider implements PgProvider {
    private static final String CANCEL_REASON = "고객이 취소를 원함";
    private static final String ZERO_AMOUNT = "0";
    private final TossFeignController tossFeignController;

    @Override
    public TossPaymentsResponseDto requestPayments(CreatePaymentRequestDto paymentRequestDto) {
        if (paymentRequestDto.amount().equals(ZERO_AMOUNT)) return null;
        TossPaymentsRequestDto requestDto = TossPaymentsRequestDto.of(paymentRequestDto);
        return tossFeignController.createPayments(requestDto);
    }

    @Override
    public TossPaymentsResponseDto requestPaymentDetail(String paymentKey) {
        if (paymentKey.isEmpty()) return null;
        return tossFeignController.getPaymentDetail(paymentKey);
    }

    @Override
    public TossPaymentsResponseDto cancelPayments(RefundType refundType, String paymentKey, Integer cancelAmount) {
        if (paymentKey.isEmpty() || cancelAmount == 0) return null;
        TossPaymentsCancelRequestDto requestDto = TossPaymentsCancelRequestDto.of(refundType, CANCEL_REASON, cancelAmount);
        return tossFeignController.cancelPayments(paymentKey, requestDto);
    }
}
