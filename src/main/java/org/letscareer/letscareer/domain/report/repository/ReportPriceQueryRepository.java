package org.letscareer.letscareer.domain.report.repository;

import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;

import java.util.Optional;

public interface ReportPriceQueryRepository {
    Optional<PriceDetailVo> findPriceDetailVoByReportId(Long reportId);
}
