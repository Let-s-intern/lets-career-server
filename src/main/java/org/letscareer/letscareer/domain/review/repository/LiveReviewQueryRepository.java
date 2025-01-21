package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.vo.LiveReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.LiveReviewVo;

import java.util.List;
import java.util.Optional;

public interface LiveReviewQueryRepository {
    List<LiveReviewAdminVo> findAllLiveReviewAdminVos();
    Optional<LiveReviewVo> findLiveReviewVoByReviewId(Long reviewId);
}
