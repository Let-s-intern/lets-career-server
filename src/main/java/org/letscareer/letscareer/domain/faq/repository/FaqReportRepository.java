package org.letscareer.letscareer.domain.faq.repository;

import org.letscareer.letscareer.domain.faq.entity.FaqReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqReportRepository extends JpaRepository<FaqReport, Long> {
}
