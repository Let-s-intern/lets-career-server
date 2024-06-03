package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.review.vo.ReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReviewQueryRepository {
    Optional<ReviewDetailVo> findReviewVo(Long reviewId);

    Page<ReviewVo> findChallengeReviewVos(Long challengeId, Pageable pageable);

    Page<ReviewVo> findLiveReviewVos(Long liveId, Pageable pageable);
}
