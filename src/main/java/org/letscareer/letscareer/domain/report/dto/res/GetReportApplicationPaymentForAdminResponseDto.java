package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationPaymentForAdminVo;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportApplicationPaymentForAdminResponseDto(
        Long paymentId,
        String orderId,
        ReportPriceType reportPriceType,
        List<String> options,
        Long feedbackApplicationId,
        String couponTitle,
        Integer finalPrice,
        Boolean isRefunded
) {
    public static GetReportApplicationPaymentForAdminResponseDto of(ReportApplicationPaymentForAdminVo vo) {
        return GetReportApplicationPaymentForAdminResponseDto.builder()
                .paymentId(vo.paymentId())
                .orderId(vo.orderId())
                .reportPriceType(vo.reportPriceType())
                .options(vo.options())
                .feedbackApplicationId(vo.feedbackApplicationId())
                .couponTitle(vo.couponTitle())
                .finalPrice(vo.finalPrice())
                .isRefunded(vo.isRefunded())
                .build();
    }
}
