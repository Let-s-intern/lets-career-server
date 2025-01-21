package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.vo.ChallengeReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ChallengeReviewVo;

import java.util.List;
import java.util.Optional;

public interface ChallengeReviewQueryRepository {
    List<ChallengeReviewAdminVo> findAllChallengeReviewAdminVos();
    Optional<ChallengeReviewVo> findChallengeReviewVoByReviewId(Long reviewId);
}
