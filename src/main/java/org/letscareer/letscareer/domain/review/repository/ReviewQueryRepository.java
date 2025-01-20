package org.letscareer.letscareer.domain.review.repository;

import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;
import org.letscareer.letscareer.domain.review.vo.ReviewInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQueryRepository {
    Page<ReviewInfoVo> findAllReviewInfoVos(List<ReviewProgramType> typeList, List<ChallengeType> challengeTypeList, Pageable pageable);
}
