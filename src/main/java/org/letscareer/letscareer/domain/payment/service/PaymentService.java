package org.letscareer.letscareer.domain.payment.service;

import org.letscareer.letscareer.domain.payment.dto.request.UpdatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentDetailResponseDto;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    GetPaymentDetailResponseDto getPaymentDetail(Long applicationId, Long paymentId);
    void updatePayment(Long paymentId, ProgramType programType, UpdatePaymentRequestDto updatePaymentRequestDto);
}
