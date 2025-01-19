package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.entity.LiveReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveReviewRepository extends JpaRepository<LiveReview, Long>, LiveReviewQueryRepository {
}
