package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.entity.BlogReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogReviewRepository extends JpaRepository<BlogReview, Long> {
}
