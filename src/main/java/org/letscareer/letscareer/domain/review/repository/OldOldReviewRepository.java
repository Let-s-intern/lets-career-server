package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.entity.OldReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OldOldReviewRepository extends JpaRepository<OldReview, Long>, OldReviewQueryRepository {
}
