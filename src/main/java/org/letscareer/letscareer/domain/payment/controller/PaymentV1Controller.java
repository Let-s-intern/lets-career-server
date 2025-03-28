package org.letscareer.letscareer.domain.payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.payment.dto.request.UpdatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentDetailResponseDto;
import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentsResponseDto;
import org.letscareer.letscareer.domain.payment.service.PaymentService;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
@RestController
public class PaymentV1Controller {
    private final PaymentService paymentService;

    @Operation(summary = "결제 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetPaymentsResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getPayments(@CurrentUser final User user) {
        final GetPaymentsResponseDto responseDto = paymentService.getPayments(user);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "결제 내역 상세", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetPaymentDetailResponseDto.class)))
    })
    @GetMapping("/{paymentId}")
    public ResponseEntity<SuccessResponse<?>> getPaymentDetail(@PathVariable final Long paymentId) {
        final GetPaymentDetailResponseDto responseDto = paymentService.getPaymentDetail(paymentId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 결제 내역 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{paymentId}")
    public ResponseEntity<SuccessResponse<?>> updatePayment(@PathVariable final Long paymentId,
                                                            @RequestParam(name = "type") final ProgramType programType,
                                                            @RequestBody final UpdatePaymentRequestDto updatePaymentRequestDto) {
        paymentService.updatePayment(paymentId, programType, updatePaymentRequestDto);
        return SuccessResponse.ok(null);
    }
}
