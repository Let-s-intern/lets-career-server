package org.letscareer.letscareer.domain.report.repository;

import org.letscareer.letscareer.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportQueryRepository {
}
