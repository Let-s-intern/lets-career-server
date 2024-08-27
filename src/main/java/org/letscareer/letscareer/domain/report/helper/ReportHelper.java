package org.letscareer.letscareer.domain.report.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationOptionForAdminVo;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.repository.ReportRepository;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.vo.*;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.report.error.ReportErrorCode.REPORT_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ReportHelper {
    private final ReportRepository reportRepository;

    public Report findReportByReportIdOrThrow(Long reportId) {
        return reportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_NOT_FOUND));
    }

    public Page<ReportForAdminVo> findReportForAdminVos(Pageable pageable) {
        return reportRepository.findReportForAdminVos(pageable);
    }

    public ReportDetailForAdminVo findReportDetailForAdminVoOrThrow(Long reportId) {
        return reportRepository.findReportDetailForAdminVo(reportId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_NOT_FOUND));
    }

    public Page<ReportApplicationForAdminVo> findReportApplicationForAdminVos(Long reportId, ReportType reportType, ReportPriceType priceType, Pageable pageable) {
        return reportRepository.findReportApplicationForAdminVos(reportId, reportType, priceType, pageable);
    }

    public List<ReportApplicationOptionForAdminVo> findReportApplicationPaymentForAdminVos(Long reportId, Long applicationId, ReportType reportType, ReportPriceType priceType, String code) {
        return reportRepository.findReportApplicationPaymentForAdminVos(reportId, applicationId, reportType, priceType, code);
    }

    public ReportDetailVo findReportDetailVoOrThrow(Long reportId) {
        return reportRepository.findReportDetailVo(reportId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_NOT_FOUND));
    }

    public ReportDetailVo findReportDetailByReportTypeVoForVisibleOrThrow(ReportType reportType) {
        return reportRepository.findReportDetailByReportTypeVoForVisible(reportType)
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

    public Report createReportAndSave(CreateReportRequestDto requestDto) {
        Report report = Report.createReport(requestDto);
        return reportRepository.save(report);
    }

    public void deleteReport(Report report) {
        reportRepository.delete(report);
    }
}
