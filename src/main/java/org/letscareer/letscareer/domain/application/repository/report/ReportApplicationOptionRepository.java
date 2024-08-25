package org.letscareer.letscareer.domain.application.repository.report;

import org.letscareer.letscareer.domain.application.entity.report.ReportApplicationOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportApplicationOptionRepository extends JpaRepository<ReportApplicationOption, Long>, ReportApplicationOptionQueryRepository {
}
