package org.letscareer.letscareer.domain.report.repository;

import org.letscareer.letscareer.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportQueryRepository {
}
