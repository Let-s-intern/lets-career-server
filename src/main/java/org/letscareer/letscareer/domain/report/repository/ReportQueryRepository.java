package org.letscareer.letscareer.domain.report.repository;

import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.domain.report.vo.ReportDetailForAdminVo;
import org.letscareer.letscareer.domain.report.vo.ReportForAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReportQueryRepository {
    Page<ReportForAdminVo> findReportForAdminVos(Pageable pageable);
    Optional<ReportDetailForAdminVo> findReportDetailForAdminVo(Long reportId);
    Page<ReportApplicationForAdminVo> findReportApplicationForAdminVos(Long reportId, Pageable pageable);
}
