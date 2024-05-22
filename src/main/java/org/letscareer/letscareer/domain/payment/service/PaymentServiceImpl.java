package org.letscareer.letscareer.domain.payment.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.payment.dto.request.UpdatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentHelper paymentHelper;

    @Operation(summary = "결제 내역 업데이트", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @Override
    public void updatePayment(Long paymentId, UpdatePaymentRequestDto updatePaymentRequestDto) {
        Payment payment = paymentHelper.findPaymentByIdOrThrow(paymentId);
        payment.updatePayment(updatePaymentRequestDto);
    }
}
