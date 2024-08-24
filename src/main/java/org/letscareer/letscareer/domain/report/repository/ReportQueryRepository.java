package org.letscareer.letscareer.domain.report.repository;

import org.letscareer.letscareer.domain.report.vo.ReportDetailForAdminVo;
import org.letscareer.letscareer.domain.report.vo.ReportForAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReportQueryRepository {
    Page<ReportForAdminVo> findReportForAdminInfos(Pageable pageable);
    Optional<ReportDetailForAdminVo> findReportDetailForAdminVo(Long reportId);
}
