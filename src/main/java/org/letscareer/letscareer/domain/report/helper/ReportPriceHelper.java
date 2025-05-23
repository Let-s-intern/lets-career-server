package org.letscareer.letscareer.domain.report.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.payment.type.ReportRefundType;
import org.letscareer.letscareer.domain.price.error.PriceErrorCode;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportApplicationRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportPriceRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportFeedback;
import org.letscareer.letscareer.domain.report.entity.ReportOption;
import org.letscareer.letscareer.domain.report.entity.ReportPrice;
import org.letscareer.letscareer.domain.report.repository.ReportPriceRepository;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationOptionPriceVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.price.error.PriceErrorCode.INVALID_PRICE;

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

    public void validatePrice(ReportPrice price, ReportFeedback reportFeedback, Coupon coupon, List<ReportOption> reportOptionList, Boolean isFeedbackApplied, String amount) {
        if(coupon != null && coupon.getDiscount() == -1) return;
        int reportPrice = price.getPrice() - price.getDiscountPrice();
        int reportFeedbackPrice = (isFeedbackApplied) ? reportFeedback.getFeedbackPrice() - reportFeedback.getFeedbackDiscountPrice() : 0;
        int reportOptionPrice = reportOptionList.stream()
                .mapToInt(reportOption -> reportOption.getPrice() - reportOption.getDiscountPrice())
                .sum();
        int couponPrice = (coupon != null) ? coupon.getDiscount() : 0;
        int finalPrice = reportPrice + reportFeedbackPrice + reportOptionPrice - couponPrice;

        if (finalPrice != Integer.parseInt(amount)) {
            throw new InvalidValueException(INVALID_PRICE);
        }
    }

    public int calculateReportCancelAmount(ReportApplication reportApplication, List<ReportApplicationOptionPriceVo> reportApplicationOptionPriceVos, Coupon coupon, ReportRefundType refundType) {
        if(refundType.equals(ReportRefundType.ZERO)) return 0;
        int couponPrice = Objects.isNull(coupon) ? 0 : coupon.getDiscount();
        couponPrice = (couponPrice == -1) ? 0 : couponPrice;
        int totalOptionPrice = 0;
        totalOptionPrice = reportApplicationOptionPriceVos.stream().mapToInt(optionPriceVo -> (optionPriceVo.price() - optionPriceVo.discountPrice())).sum();
        int regularPrice = (reportApplication.getPrice() - reportApplication.getDiscountPrice() + totalOptionPrice);
        int refundPrice = ((int) (regularPrice * refundType.getPercent())) - couponPrice;
        return Math.max(refundPrice, 0);
    }

    public int calculateFeedbackCancelAmount(ReportFeedbackApplication reportFeedbackApplication, ReportRefundType refundType) {
        if(refundType.equals(ReportRefundType.ZERO)) return 0;
        int refundPrice = (int) ((reportFeedbackApplication.getPrice() - reportFeedbackApplication.getDiscountPrice()) * refundType.getPercent());
        return Math.max(refundPrice, 0);
    }

    public PriceDetailVo findReportPriceDetailVoByReportId(Long reportId) {
        return reportPriceRepository.findPriceDetailVoByReportId(reportId)
                .orElseThrow(() -> new EntityNotFoundException(PriceErrorCode.REPORT_PRICE_NOT_FOUND));
    }
}
