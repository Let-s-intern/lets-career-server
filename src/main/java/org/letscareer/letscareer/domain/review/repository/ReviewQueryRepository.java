package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.vo.ReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryRepository {
    Optional<ReviewDetailVo> findReviewVo(Long reviewId);

    Page<ReviewAdminVo> findChallengeReviewAdminVos(Long challengeId, Pageable pageable);

    Page<ReviewVo> findChallengeReviewVos(Pageable pageable);

    Page<ReviewVo> findLiveReviewVos(Pageable pageable);

    Page<ReviewAdminVo> findLiveReviewAdminVos(Long liveId, Pageable pageable);

    List<String> findReviewContentByLiveId(Long liveId);

    List<ReviewDetailVo> findAllReviewDetailVosByProgramType(ProgramType programType);
}
