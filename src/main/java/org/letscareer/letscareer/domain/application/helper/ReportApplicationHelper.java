package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplicationOption;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.repository.report.ReportApplicationOptionRepository;
import org.letscareer.letscareer.domain.application.repository.report.ReportApplicationRepository;
import org.letscareer.letscareer.domain.application.repository.report.ReportFeedbackApplicationRepository;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportApplicationRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportFeedback;
import org.letscareer.letscareer.domain.report.entity.ReportOption;
import org.letscareer.letscareer.domain.report.entity.ReportPrice;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationOptionPriceVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.report.error.ReportErrorCode.REPORT_APPLICATION_NOT_FOUND;
@RequiredArgsConstructor
@Component
public class ReportApplicationHelper {
    private final ReportApplicationRepository reportApplicationRepository;
    private final ReportApplicationOptionRepository reportApplicationOptionRepository;
    private final ReportFeedbackApplicationRepository reportFeedbackApplicationRepository;

    public ReportApplication createReportApplicationAndSave(CreateReportApplicationRequestDto requestDto,
                                                            Report report,
                                                            ReportPrice reportPrice,
                                                            User user) {
        ReportApplication reportApplication
                = ReportApplication.createReportApplication(requestDto, report, reportPrice, user);
        return reportApplicationRepository.save(reportApplication);
    }

    public ReportApplicationOption createReportApplicationOptionAndSave(ReportApplication reportApplication,
                                                                        ReportOption reportOption) {
        ReportApplicationOption reportApplicationOption
                = ReportApplicationOption.createReportApplicationOption(reportApplication, reportOption);
        return reportApplicationOptionRepository.save(reportApplicationOption);
    }

    public ReportFeedbackApplication createReportFeedbackApplicationAndSave(CreateReportApplicationRequestDto requestDto,
                                                                            ReportFeedback reportFeedback,
                                                                            ReportApplication reportApplication) {
        if (Objects.isNull(requestDto.isFeedbackApplied())) return null;
        if (requestDto.isFeedbackApplied() == Boolean.FALSE) return null;
        ReportFeedbackApplication reportFeedbackApplication
                = ReportFeedbackApplication.createReportFeedbackApplication(requestDto, reportFeedback, reportApplication);
        return reportFeedbackApplicationRepository.save(reportFeedbackApplication);
    }

    public ReportApplication findReportApplicationByReportApplicationIdOrElseNull(Long applicationId) {
        return reportApplicationRepository.findById(applicationId).orElse(null);
    }

    public ReportApplication findReportApplicationByReportApplicationIdOrThrow(Long reportApplicationId) {
        return reportApplicationRepository.findById(reportApplicationId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_APPLICATION_NOT_FOUND));
    }

    public ReportFeedbackApplication findReportFeedbackApplicationByApplicationId(Long reportApplicationId) {
        return reportFeedbackApplicationRepository.findByReportApplicationId(reportApplicationId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_APPLICATION_NOT_FOUND));
    }

    public List<ReportApplicationOptionPriceVo> findAllReportApplicationOptionPriceVosByReportApplicationId(Long reportApplicationId) {
        return reportApplicationOptionRepository.findAllReportApplicationOptionPriceVosByReportApplicationId(reportApplicationId);
    }

    public List<ReportApplicationOption> findAllReportApplicationOptionsByApplicationId(Long reportApplicationId) {
        return reportApplicationOptionRepository.findAllReportApplicationOptionsByApplicationId(reportApplicationId);
    }

    public List<Long> findIngNotificationReportApplicationIds() {
        return reportApplicationRepository.findAllIngNotificationReportApplicationId();
    }

    public List<Long> findRemindNotificationReportApplicationIds() {
        return reportApplicationRepository.findAllRemindNotificationReportApplicationId();
    }

    public List<Long> findLastRemindNotificationReportApplicationIds() {
        return reportApplicationRepository.findAllLastRemindNotificationReportApplicationId();
    }

    public List<Long> findAutoRefundNotificationReportApplicationIds() {
        return reportApplicationRepository.findAllAutoRefundNotificationReportApplicationId();
    }

    public List<Long> findReviewNotificationReportApplicationIds() {
        return reportApplicationRepository.findAllReviewNotificationReportApplicationId();
    }

    public void deleteReportApplication(ReportApplication reportApplication) {
        reportApplicationRepository.delete(reportApplication);
    }
}
