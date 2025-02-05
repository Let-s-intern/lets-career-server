package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.entity.ChallengeReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeReviewRepository extends JpaRepository<ChallengeReview, Long>, ChallengeReviewQueryRepository {
}
