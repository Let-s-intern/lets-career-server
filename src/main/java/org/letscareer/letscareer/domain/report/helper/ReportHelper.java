package org.letscareer.letscareer.domain.report.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationPaymentForAdminVo;
import org.letscareer.letscareer.domain.application.vo.ReportFeedbackApplicationForAdminVo;
import org.letscareer.letscareer.domain.report.repository.ReportRepository;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.vo.*;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static org.letscareer.letscareer.domain.report.error.ReportErrorCode.REPORT_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ReportHelper {
    private final ReportRepository reportRepository;

    public Page<ReportForAdminVo> findReportForAdminVos(Pageable pageable) {
        return reportRepository.findReportForAdminVos(pageable);
    }

    public ReportDetailForAdminVo findReportDetailForAdminVoOrThrow(Long reportId) {
        return reportRepository.findReportDetailForAdminVo(reportId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_NOT_FOUND));
    }

    public Page<ReportApplicationForAdminVo> findReportApplicationForAdminVos(Long reportId, Pageable pageable) {
        return reportRepository.findReportApplicationForAdminVos(reportId, pageable);
    }

    public Page<ReportFeedbackApplicationForAdminVo> findReportFeedbackApplicationForAdminVos(Long reportId, Pageable pageable) {
        return reportRepository.findReportFeedbackApplicationForAdminVos(reportId, pageable);
    }

    public ReportApplicationPaymentForAdminVo findReportApplicationPaymentForAdminVoOrThrow(Long reportId, Long applicationId) {
        return reportRepository.findReportApplicationPaymentForAdminVo(reportId, applicationId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_NOT_FOUND));
    }

    public ReportDetailVo findReportDetailVoOrThrow(Long reportId) {
        return reportRepository.findReportDetailVo(reportId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_NOT_FOUND));
    }

    public ReportPriceDetailVo findReportPriceDetailVoOrThrow(Long reportId) {
        return reportRepository.findReportPriceDetailVo(reportId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_NOT_FOUND));
    }

    public Page<MyReportVo> findMyReportVos(User user, ReportType reportType, Pageable pageable) {
        return reportRepository.findMyReportVos(user.getId(), reportType, pageable);
    }

    public Page<MyReportFeedbackVo> findMyReportFeedbackVos(User user, ReportType reportType, Pageable pageable) {
        return reportRepository.findMyReportFeedbackVos(user.getId(), reportType, pageable);
    }
}
