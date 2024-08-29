package org.letscareer.letscareer.domain.report.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.payment.type.ReportRefundType;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportPriceRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportPrice;
import org.letscareer.letscareer.domain.report.repository.ReportPriceRepository;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationOptionPriceVo;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.APPLICATION_CANNOT_CANCELED;

@RequiredArgsConstructor
@Component
public class ReportPriceHelper {
    private final ReportPriceRepository reportPriceRepository;

    public ReportPrice createReportPriceAndSave(CreateReportPriceRequestDto requestDto, Report report) {
        ReportPrice reportPrice = ReportPrice.createReportPrice(requestDto, report);
        return reportPriceRepository.save(reportPrice);
    }

    public void deleteAllReportPricesByReportId(Long reportId) {
        reportPriceRepository.deleteAllByReportId(reportId);
    }

    public int calculateReportCancelAmount(ReportApplication reportApplication, List<ReportApplicationOptionPriceVo> reportApplicationOptionPriceVos, Coupon coupon, ReportRefundType refundType) {
        if(refundType.equals(ReportRefundType.ZERO)) throw new InvalidValueException(APPLICATION_CANNOT_CANCELED);
        int couponPrice = Objects.isNull(coupon) ? 0 : coupon.getDiscount();
        couponPrice = (couponPrice == -1) ? 0 : couponPrice;
        int totalOptionPrice = 0;
        totalOptionPrice = reportApplicationOptionPriceVos.stream().mapToInt(optionPriceVo -> (optionPriceVo.price() - optionPriceVo.discountPrice())).sum();
        int regularPrice = (reportApplication.getPrice() - reportApplication.getDiscountPrice() + totalOptionPrice) + couponPrice;
        int refundPrice = ((int) (regularPrice * refundType.getPercent())) - couponPrice;
        return Math.max(refundPrice, 0);
    }

    public int calculateFeedbackCancelAmount(ReportFeedbackApplication reportFeedbackApplication, ReportRefundType refundType) {
        if(refundType.equals(ReportRefundType.ZERO)) throw new InvalidValueException(APPLICATION_CANNOT_CANCELED);
        int refundPrice = (int) ((reportFeedbackApplication.getPrice() - reportFeedbackApplication.getDiscountPrice()) * refundType.getPercent());
        return Math.max(refundPrice, 0);
    }
}
