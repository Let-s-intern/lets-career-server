package org.letscareer.letscareer.domain.pg.provider;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.pg.dto.request.TossPaymentsRequestDto;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.pg.mapper.TossMapper;
import org.letscareer.letscareer.global.common.utils.toss.TossFeignController;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("TOSS")
public class TossProvider implements PgProvider {
    private final TossFeignController tossFeignController;
    private final TossMapper tossMapper;

    @Override
    public void requestPayments(CreatePaymentRequestDto paymentRequestDto) {
        TossPaymentsRequestDto requestDto = tossMapper.toTossPaymentsRequestDto(paymentRequestDto);
        TossPaymentsResponseDto responseDto = tossFeignController.createPayments(requestDto);
    }
}
