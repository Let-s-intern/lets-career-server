package org.letscareer.letscareer.domain.review.service;

import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewCountResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VWReviewService {
    GetReviewResponseDto getReviews(List<ReviewProgramType> typeList, List<ChallengeType> challengeTypeList, String liveJob, Pageable pageable);
    GetReviewCountResponseDto getReviewCount();
}
