package org.letscareer.letscareer.domain.report.repository;

import org.letscareer.letscareer.domain.report.vo.ReportForAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReportQueryRepository {
    Page<ReportForAdminVo> findReportForAdminInfos(Pageable pageable);
}
