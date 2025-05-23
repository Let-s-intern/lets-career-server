package org.letscareer.letscareer.domain.report.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationOptionForAdminVo;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.UpdateReportRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportPrice;
import org.letscareer.letscareer.domain.report.repository.ReportRepository;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.vo.*;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.domain.report.error.ReportErrorCode.*;

@RequiredArgsConstructor
@Component
public class ReportHelper {
    private final ReportRepository reportRepository;
    public static final List<ReportType> reportTypeList = Arrays.asList(ReportType.values());

    public void validateUpdateVisibleDate(UpdateReportRequestDto requestDto) {
        if (Objects.isNull(requestDto.reportType())) return;
        if (reportRepository.existByReportTypeAndVisibleDate(requestDto.reportType()).isPresent())
            throw new ConflictException(REPORT_CONFLICT_VISIBLE_DATE);
    }

    public void validateDuplicateReportApplication(User user, Long reportId) {
        Optional<ReportApplication> reportApplication = reportRepository.findReportByIdAndUser(user, reportId);
        if(reportApplication.isPresent())
            throw new ConflictException(DUPLICATE_REPORT_APPLICATION);
    }

    public ReportApplicationVo findReportApplicationVoByApplicationId(Long applicationId) {
        return reportRepository.findReportApplicationVoByApplicationId(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_APPLICATION_NOT_FOUND));
    }

    public ReportPaymentVo findReportPaymentVoByApplicationId(Long applicationId) {
        return reportRepository.findReportPaymentVoByApplicationId(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_APPLICATION_NOT_FOUND));
    }

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

    public Page<ReportApplicationForAdminVo> findReportApplicationForAdminVos(Long reportId, ReportType reportType, ReportPriceType priceType, Boolean isApplyFeedback, Pageable pageable) {
        return reportRepository.findReportApplicationForAdminVos(reportId, reportType, priceType, isApplyFeedback, pageable);
    }

    public List<ReportApplicationOptionForAdminVo> findReportApplicationPaymentForAdminVos(Long reportId, Long applicationId, ReportType reportType, ReportPriceType priceType, String code) {
        return reportRepository.findReportApplicationPaymentForAdminVos(reportId, applicationId, reportType, priceType, code);
    }

    public ReportDetailVo findReportDetailVoOrThrow(Long reportId) {
        return reportRepository.findReportDetailVo(reportId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_NOT_FOUND));
    }

    public List<ReportDetailVo> findAllReportDetailByReportTypeVoForVisibleOrNull(ReportType reportType) {
        return reportRepository.findAllReportDetailByReportTypeVoForVisible(reportType);
    }

    public ReportPriceDetailVo findReportPriceDetailVoOrThrow(Long reportId) {
        return reportRepository.findReportPriceDetailVo(reportId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_NOT_FOUND));
    }

    public Page<MyReportVo> findMyReportVos(User user, ReportType reportType, Pageable pageable) {
        return reportRepository.findMyReportVos(user.getId(), reportType, pageable);
    }

    public ReportPrice findReportPriceByReportIdAndType(Long reportId, ReportPriceType reportPriceType) {
        return reportRepository.findReportPriceByReportIdAndType(reportId, reportPriceType);
    }

    public Report createReportAndSave(CreateReportRequestDto requestDto) {
        Report report = Report.createReport(requestDto);
        return reportRepository.save(report);
    }

    public void deleteReport(Report report) {
        reportRepository.delete(report);
    }

    public ReportTitleVo findReportTitleVoOrThrow(Long reportId) {
        return reportRepository.findReportTitleVo(reportId)
                .orElseThrow(() -> new EntityNotFoundException(REPORT_NOT_FOUND));
    }

    public List<ReportRecommendVo> findAllReportRecommendVos() {
        return reportTypeList.stream()
                .map(reportType -> reportRepository.findReportRecommendVoByReportType(reportType))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
