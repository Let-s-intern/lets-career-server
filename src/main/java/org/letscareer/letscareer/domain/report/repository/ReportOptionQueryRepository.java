package org.letscareer.letscareer.domain.report.repository;

import org.letscareer.letscareer.domain.report.entity.ReportOption;

import java.util.List;

public interface ReportOptionQueryRepository {
    List<ReportOption> findReportOptionsByReportIdAndOptionIds(Long reportId, List<Long> optionIds);
}
