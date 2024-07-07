package org.letscareer.letscareer.domain.payment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.vo.PaymentProgramVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetPaymentProgramResponseDto(
        Long paymentId,
        String title,
        String thumbnail,
        Integer price
) {
    public static GetPaymentProgramResponseDto of(PaymentProgramVo vo) {
        return GetPaymentProgramResponseDto.builder()
                .paymentId(vo.paymentId())
                .title(vo.title())
                .thumbnail(vo.thumbnail())
                .price(vo.price())
                .build();
    }
}
