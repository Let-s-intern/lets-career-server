package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.review.repository.ReviewRepository;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;
import org.letscareer.letscareer.domain.review.vo.ReviewInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ReviewHelper {
    private final ReviewRepository reviewRepository;

    public Page<ReviewInfoVo> getReviewInfoVos(List<ReviewProgramType> typeList, List<ChallengeType> challengeTypeList, String liveJob, Pageable pageable) {
        return reviewRepository.findAllReviewInfoVos(typeList, challengeTypeList, liveJob, pageable);
    }

    public Long countReviews() {
        return reviewRepository.countReviews();
    }
}
