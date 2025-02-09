package org.letscareer.letscareer.domain.payment.mapper;

import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentDetailResponseDto;
import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentProgramResponseDto;
import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentResponseDto;
import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentsResponseDto;
import org.letscareer.letscareer.domain.payment.vo.PaymentDetailVo;
import org.letscareer.letscareer.domain.payment.vo.PaymentProgramVo;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;
import org.letscareer.letscareer.domain.program.vo.ProgramSimpleVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMapper {
    public GetPaymentDetailResponseDto toGetPaymentDetailResponseDto(ProgramSimpleVo programSimpleVo, PriceDetailVo priceInfo, PaymentDetailVo paymentInfo, TossPaymentsResponseDto tossInfo) {
        return GetPaymentDetailResponseDto.of(programSimpleVo, priceInfo, paymentInfo, tossInfo);
    }

    public GetPaymentProgramResponseDto toGetPaymentProgramResponseDto(PaymentProgramVo programInfo, Integer paybackPrice) {
        return GetPaymentProgramResponseDto.of(programInfo, paybackPrice);
    }

    public GetPaymentResponseDto toGetPaymentResponseDto(PaymentProgramVo programInfo,
                                                         Integer paybackPrice,
                                                         TossPaymentsResponseDto tossInfo) {
        GetPaymentProgramResponseDto programResponseDto = toGetPaymentProgramResponseDto(programInfo, paybackPrice);
        return GetPaymentResponseDto.of(programResponseDto, tossInfo);
    }

    public GetPaymentsResponseDto toGetPaymentsResponseDto(List<GetPaymentResponseDto> payments) {
        return GetPaymentsResponseDto.of(payments);
    }
}
