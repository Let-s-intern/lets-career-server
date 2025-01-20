package org.letscareer.letscareer.domain.review.repository.old;

import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OldReviewQueryRepository {
    Optional<OldReviewDetailVo> findReviewVo(Long reviewId);

    Page<OldReviewAdminVo> findChallengeReviewAdminVos(Long challengeId, Pageable pageable);

    Page<OldReviewVo> findChallengeReviewVos(Pageable pageable);

    Page<OldReviewVo> findLiveReviewVos(Pageable pageable);

    Page<OldReviewAdminVo> findLiveReviewAdminVos(Long liveId, Pageable pageable);

    List<String> findReviewContentByLiveId(Long liveId);

    List<OldReviewAdminVo> findAllReviewAdminVosByProgramType(Boolean isVisible, ProgramType programType, List<String> sortBy);
}
