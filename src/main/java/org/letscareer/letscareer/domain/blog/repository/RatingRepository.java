package org.letscareer.letscareer.domain.blog.repository;

import org.letscareer.letscareer.domain.blog.entity.BlogRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<BlogRating, Long>, RatingQueryRepository {
}
