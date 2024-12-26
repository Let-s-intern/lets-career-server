package org.letscareer.letscareer.domain.application.repository.report;

import org.letscareer.letscareer.domain.application.entity.report.ReportApplicationOption;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationOptionPriceVo;

import java.util.List;

public interface ReportApplicationOptionQueryRepository {
    List<ReportApplicationOptionPriceVo> findAllReportApplicationOptionPriceVosByReportApplicationId(Long reportApplicationId);
    List<ReportApplicationOption> findAllReportApplicationOptionsByApplicationId(Long reportApplicationId);
}
