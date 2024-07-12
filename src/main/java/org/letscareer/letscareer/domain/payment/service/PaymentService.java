package org.letscareer.letscareer.domain.payment.service;

import org.letscareer.letscareer.domain.payment.dto.request.UpdatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentDetailResponseDto;
import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentsResponseDto;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    GetPaymentsResponseDto getPayments(User user);
    GetPaymentDetailResponseDto getPaymentDetail(Long paymentId);
    void updatePayment(Long paymentId, ProgramType programType, UpdatePaymentRequestDto updatePaymentRequestDto);
}
