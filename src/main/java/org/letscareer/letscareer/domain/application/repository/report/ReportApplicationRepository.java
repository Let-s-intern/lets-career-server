package org.letscareer.letscareer.domain.application.repository.report;

import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportApplicationRepository extends JpaRepository<ReportApplication, Long>, ReportApplicationQueryRepository {
}
