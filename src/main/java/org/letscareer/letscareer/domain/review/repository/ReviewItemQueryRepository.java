package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.vo.ReviewItemAdminVo;

import java.util.List;

public interface ReviewItemQueryRepository {
    List<ReviewItemAdminVo> findAllReviewItemAdminVosByReviewId(Long reviewId);
}
