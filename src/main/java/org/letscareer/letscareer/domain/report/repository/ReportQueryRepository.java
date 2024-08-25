package org.letscareer.letscareer.domain.report.repository;

import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationPaymentForAdminVo;
import org.letscareer.letscareer.domain.application.vo.ReportFeedbackApplicationForAdminVo;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReportQueryRepository {
    Page<ReportForAdminVo> findReportForAdminVos(Pageable pageable);

    Optional<ReportDetailForAdminVo> findReportDetailForAdminVo(Long reportId);

    Page<ReportApplicationForAdminVo> findReportApplicationForAdminVos(Long reportId, Pageable pageable);

    Page<ReportFeedbackApplicationForAdminVo> findReportFeedbackApplicationForAdminVos(Long reportId, Pageable pageable);

    Optional<ReportApplicationPaymentForAdminVo> findReportApplicationPaymentForAdminVo(Long reportId, Long applicationId);

    Optional<ReportDetailVo> findReportDetailVo(Long reportId);

    Optional<ReportPriceDetailVo> findReportPriceDetailVo(Long reportId);

    Page<MyReportVo> findMyReportVos(Long userId, ReportType reportType, Pageable pageable);

    Page<MyReportFeedbackVo> findMyReportFeedbackVos(Long userId, ReportType reportType, Pageable pageable);
}
