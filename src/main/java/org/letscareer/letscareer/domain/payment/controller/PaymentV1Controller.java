package org.letscareer.letscareer.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.payment.dto.request.UpdatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.service.PaymentService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
@RestController
public class PaymentV1Controller {
    private final PaymentService paymentService;

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updatePayment(@PathVariable(name = "id") final Long paymentId,
                                                            @RequestBody final UpdatePaymentRequestDto updatePaymentRequestDto) {
        paymentService.updatePayment(paymentId, updatePaymentRequestDto);
        return SuccessResponse.ok(null);
    }
}
