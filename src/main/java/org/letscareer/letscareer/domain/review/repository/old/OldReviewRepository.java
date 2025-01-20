package org.letscareer.letscareer.domain.review.repository.old;

import org.letscareer.letscareer.domain.review.entity.old.OldReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OldReviewRepository extends JpaRepository<OldReview, Long>, OldReviewQueryRepository {
}
