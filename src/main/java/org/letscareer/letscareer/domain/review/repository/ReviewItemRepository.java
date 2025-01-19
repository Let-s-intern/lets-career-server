package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.entity.ReviewItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewItemRepository extends JpaRepository<ReviewItem, Long>, ReviewItemQueryRepository {
}
