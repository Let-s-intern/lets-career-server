package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {
}
