package org.letscareer.letscareer.domain.payment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.vo.PaymentProgramVo;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record GetPaymentProgramResponseDto(
        Long paymentId,
        Long applicationId,
        ProgramType programType,
        ReportType reportType,
        String title,
        String thumbnail,
        Integer price,
        Integer paybackPrice,
        Integer optionPrice,
        Boolean isCanceled,
        Boolean isRefunded,
        LocalDateTime createDate
) {
    public static GetPaymentProgramResponseDto of(PaymentProgramVo vo, Integer paybackPrice) {
        return GetPaymentProgramResponseDto.builder()
                .paymentId(vo.paymentId())
                .applicationId(vo.applicationId())
                .programType(ProgramType.valueOf(vo.programType()))
                .reportType(vo.reportType())
                .title(vo.title())
                .thumbnail(vo.thumbnail())
                .price(vo.price())
                .paybackPrice(paybackPrice)
                .optionPrice(vo.optionPrice())
                .isCanceled(vo.isCanceled())
                .isRefunded(vo.isRefunded())
                .createDate(vo.createDate())
                .build();
    }
}
