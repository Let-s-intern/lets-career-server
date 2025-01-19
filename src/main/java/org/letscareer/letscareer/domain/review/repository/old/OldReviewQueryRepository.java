package org.letscareer.letscareer.domain.review.repository.old;

import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.vo.old.ReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.old.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.old.ReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OldReviewQueryRepository {
    Optional<ReviewDetailVo> findReviewVo(Long reviewId);

    Page<ReviewAdminVo> findChallengeReviewAdminVos(Long challengeId, Pageable pageable);

    Page<ReviewVo> findChallengeReviewVos(Pageable pageable);

    Page<ReviewVo> findLiveReviewVos(Pageable pageable);

    Page<ReviewAdminVo> findLiveReviewAdminVos(Long liveId, Pageable pageable);

    List<String> findReviewContentByLiveId(Long liveId);

    List<ReviewAdminVo> findAllReviewAdminVosByProgramType(Boolean isVisible, ProgramType programType, List<String> sortBy);
}
