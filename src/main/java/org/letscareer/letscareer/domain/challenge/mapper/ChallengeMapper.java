package org.letscareer.letscareer.domain.challenge.mapper;

import org.letscareer.letscareer.domain.challenge.dto.response.*;
import org.letscareer.letscareer.domain.challenge.vo.*;
import org.letscareer.letscareer.domain.challengeguide.vo.ChallengeGuideVo;
import org.letscareer.letscareer.domain.classification.vo.ChallengeClassificationDetailVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.domain.user.entity.User;
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

    public GetChallengeThumbnailResponseDto toChallengeThumbnailVo(ChallengeThumbnailVo challengeThumbnailVo) {
        return GetChallengeThumbnailResponseDto.of(challengeThumbnailVo);
    }

    public GetChallengeContentResponseDto toGetChallengeContentResponseDto(ChallengeContentVo challengeContentVo) {
        return GetChallengeContentResponseDto.of(challengeContentVo);
    }

    public GetChallengeApplicationFormResponseDto toGetChallengeApplicationFormResponseDto(User user,
                                                                                           ChallengeApplicationFormVo applicationFormVo,
                                                                                           List<ChallengePriceDetailVo> challengePriceDetailVos) {
        return GetChallengeApplicationFormResponseDto.of(user, applicationFormVo, challengePriceDetailVos);
    }

    public GetChallengeGuidesResponseDto toChallengeGuideAdminListResponseDto(List<ChallengeGuideVo> challengeGuideAdminList) {
        return GetChallengeGuidesResponseDto.of(challengeGuideAdminList);
    }
}
