package org.letscareer.letscareer.domain.application.dto.request;

import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;

public record CreateApplicationRequestDto(
        CreatePaymentRequestDto paymentInfo,
        String motivate,
        String question
) {
}
