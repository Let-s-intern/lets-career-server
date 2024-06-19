package org.letscareer.letscareer.domain.payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.payment.dto.request.UpdatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.service.PaymentService;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
@RestController
public class PaymentV1Controller {
    private final PaymentService paymentService;

    @Operation(summary = "[어드민] 결제 내역 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updatePayment(@PathVariable(name = "id") final Long paymentId,
                                                            @RequestParam(name = "type") final ProgramType programType,
                                                            @RequestBody final UpdatePaymentRequestDto updatePaymentRequestDto) {
        paymentService.updatePayment(paymentId, programType, updatePaymentRequestDto);
        return SuccessResponse.ok(null);
    }
}
