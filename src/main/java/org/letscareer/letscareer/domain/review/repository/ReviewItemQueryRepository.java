package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.vo.ReviewItemAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewItemVo;

import java.util.List;

public interface ReviewItemQueryRepository {
    List<ReviewItemVo> findAllReviewItemVosByReviewId(Long reviewId);
    List<ReviewItemAdminVo> findAllReviewItemAdminVosByReviewId(Long reviewId);
}
