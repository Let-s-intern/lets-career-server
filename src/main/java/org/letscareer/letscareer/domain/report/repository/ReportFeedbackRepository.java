package org.letscareer.letscareer.domain.report.repository;

import org.letscareer.letscareer.domain.report.entity.ReportFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportFeedbackRepository extends JpaRepository<ReportFeedback, Long> {
    void deleteAllByReportId(Long reportId);
}
