package org.letscareer.letscareer.domain.challenge.mapper;

import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeDetailResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeReviewResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengesResponseDto;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeDetailVo;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeProfileVo;
import org.letscareer.letscareer.domain.classification.vo.ChallengeClassificationDetailVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeMapper {

    public GetChallengeDetailResponseDto toChallengeDetailResponseDto(ChallengeDetailVo challengeInfo,
                                                                      List<ChallengeClassificationDetailVo> classificationInfo,
                                                                      List<ChallengePriceDetailVo> priceInfo,
                                                                      List<FaqDetailVo> faqInfo) {
        return GetChallengeDetailResponseDto.of(challengeInfo, classificationInfo, priceInfo, faqInfo);
    }

    public GetChallengesResponseDto toGetChallengesResponseDto(Page<ChallengeProfileVo> challengeProfileVos) {
        return GetChallengesResponseDto.of(challengeProfileVos);
    }

    public GetChallengeReviewResponseDto toGetChallengeReviewResponseDto(Page<ReviewVo> challengeReviewVos) {
        return GetChallengeReviewResponseDto.of(challengeReviewVos);
    }
}
