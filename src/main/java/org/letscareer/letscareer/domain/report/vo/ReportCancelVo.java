package org.letscareer.letscareer.domain.report.vo;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.type.ReportRefundType;

@Builder(access = AccessLevel.PRIVATE)
public record ReportCancelVo(
        ReportRefundType reportRefundType,
        Integer cancelAmount
) {
    public static ReportCancelVo of(ReportRefundType reportRefundType, Integer cancelAmount) {
        return ReportCancelVo.builder()
                .reportRefundType(reportRefundType)
                .cancelAmount(cancelAmount)
                .build();
    }
}
