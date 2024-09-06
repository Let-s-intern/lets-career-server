package org.letscareer.letscareer.domain.report.repository;

import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationOptionForAdminVo;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportPrice;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReportQueryRepository {
    Optional<Report> existByReportTypeAndVisibleDate(ReportType reportType);

    Page<ReportForAdminVo> findReportForAdminVos(Pageable pageable);

    Optional<ReportDetailForAdminVo> findReportDetailForAdminVo(Long reportId);

    Page<ReportApplicationForAdminVo> findReportApplicationForAdminVos(Long reportId, ReportType reportType, ReportPriceType priceType, Boolean isApplyFeedback, Pageable pageable);

    List<ReportApplicationOptionForAdminVo> findReportApplicationPaymentForAdminVos(Long reportId, Long applicationId, ReportType reportType, ReportPriceType priceType, String code);

    Optional<ReportDetailVo> findReportDetailVo(Long reportId);

    Optional<ReportPriceDetailVo> findReportPriceDetailVo(Long reportId);

    Page<MyReportVo> findMyReportVos(Long userId, ReportType reportType, Pageable pageable);

    Optional<ReportDetailVo> findReportDetailByReportTypeVoForVisible(ReportType reportType);

    Optional<ReportApplicationVo> findReportApplicationVoByApplicationId(Long applicationId);

    Optional<ReportPaymentVo> findReportPaymentVoByApplicationId(Long applicationId);

    ReportPrice findReportPriceByReportIdAndType(Long reportId, ReportPriceType reportPriceType);
}
