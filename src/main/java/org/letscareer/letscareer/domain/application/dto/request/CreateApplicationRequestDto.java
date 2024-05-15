package org.letscareer.letscareer.domain.application.dto.request;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;

public record CreateApplicationRequestDto(
        @NotNull CreatePaymentRequestDto paymentInfo,
        String motivate,
        String question
) {
}
