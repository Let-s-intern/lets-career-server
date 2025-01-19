package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.vo.ChallengeReviewAdminVo;

import java.util.List;

public interface ChallengeReviewQueryRepository {
    List<ChallengeReviewAdminVo> findAllChallengeReviewAdminVos();
}
