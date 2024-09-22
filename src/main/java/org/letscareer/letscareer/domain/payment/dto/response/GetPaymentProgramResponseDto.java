package org.letscareer.letscareer.domain.payment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.vo.PaymentProgramVo;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record GetPaymentProgramResponseDto(
        Long paymentId,
        Long applicationId,
        ProgramType programType,
        String title,
        String thumbnail,
        Integer price,
        Boolean isCanceled,
        LocalDateTime createDate
) {
    public static GetPaymentProgramResponseDto of(PaymentProgramVo vo) {
        return GetPaymentProgramResponseDto.builder()
                .paymentId(vo.paymentId())
                .applicationId(vo.applicationId())
                .programType(ProgramType.valueOf(vo.programType()))
                .title(vo.title())
                .thumbnail(vo.thumbnail())
                .price(vo.price())
                .isCanceled(vo.isCanceled())
                .createDate(vo.createDate())
                .build();
    }
}
