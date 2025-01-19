package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.entity.ReportReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportReviewRepository extends JpaRepository<ReportReview, Long>, ReportReviewQueryRepository {
}
