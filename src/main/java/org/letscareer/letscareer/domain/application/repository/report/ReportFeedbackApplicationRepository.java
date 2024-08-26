package org.letscareer.letscareer.domain.application.repository.report;

import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportFeedbackApplicationRepository extends JpaRepository<ReportFeedbackApplication, Long> {
    Optional<ReportFeedbackApplication> findByReportApplicationId(Long reportApplicationId);
}
