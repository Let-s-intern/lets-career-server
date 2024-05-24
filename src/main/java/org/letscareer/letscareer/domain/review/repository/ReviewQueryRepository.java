package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {
    Page<ReviewVo> findChallengeReviewVos(Long challengeId, Pageable pageable);

    Page<ReviewVo> findLiveReviewVos(Long liveId, Pageable pageable);
}
