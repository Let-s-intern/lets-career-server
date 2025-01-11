package org.letscareer.letscareer.domain.challenge.mapper;

import org.letscareer.letscareer.domain.challenge.dto.response.*;
import org.letscareer.letscareer.domain.challenge.vo.*;
import org.letscareer.letscareer.domain.challengeguide.vo.ChallengeGuideVo;
import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeVo;
import org.letscareer.letscareer.domain.classification.vo.ChallengeClassificationDetailVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.entity.PageInfo;
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

    public GetTypeChallengeResponseDto toGetTypeChallengeResponseDto(List<ChallengeSimpleProfileVo> challengeSimpleProfileVos) {
        return GetTypeChallengeResponseDto.of(challengeSimpleProfileVos);
    }

    public GetChallengeResponseDto toGetChallengesResponseDto(Page<ChallengeProfileVo> challengeProfileVos) {
        return GetChallengeResponseDto.of(challengeProfileVos);
    }

    public GetChallengeTitleResponseDto toGetChallengeTitleResponseDto(ChallengeTitleVo titleVo) {
        return GetChallengeTitleResponseDto.of(titleVo);
    }

    public GetChallengeReviewResponseDto toGetChallengeReviewResponseDto(List<GetReviewResponseDto> reviewResDtoList, PageInfo pageInfo) {
        return GetChallengeReviewResponseDto.of(reviewResDtoList, pageInfo);
    }

    public GetChallengeAdminReviewResponseDto toGetChallengeAdminReviewResponseDto(Page<ReviewAdminVo> challengeReviewVos) {
        return GetChallengeAdminReviewResponseDto.of(challengeReviewVos);
    }

    public GetChallengeThumbnailResponseDto toChallengeThumbnailVo(ChallengeThumbnailVo challengeThumbnailVo) {
        return GetChallengeThumbnailResponseDto.of(challengeThumbnailVo);
    }

    public GetChallengeContentResponseDto toGetChallengeContentResponseDto(ChallengeContentVo challengeContentVo) {
        return GetChallengeContentResponseDto.of(challengeContentVo);
    }

    public GetChallengeApplicationFormResponseDto toGetChallengeApplicationFormResponseDto(User user,
                                                                                           Boolean applied,
                                                                                           ChallengeApplicationFormVo applicationFormVo,
                                                                                           List<ChallengePriceDetailVo> challengePriceDetailVos) {
        return GetChallengeApplicationFormResponseDto.of(user, applied, applicationFormVo, challengePriceDetailVos);
    }

    public GetChallengeGuidesResponseDto toChallengeGuideAdminListResponseDto(List<ChallengeGuideVo> challengeGuideAdminList) {
        return GetChallengeGuidesResponseDto.of(challengeGuideAdminList);
    }

    public GetChallengeNoticesResponseDto toGetChallengeNoticesResponseDto(Page<ChallengeNoticeVo> challengeNoticeList) {
        return GetChallengeNoticesResponseDto.of(challengeNoticeList);
    }

    public GetChallengeEmailContentsResponseDto toGetChallengeEmailContentsResponseDto(String title, String contents) {
        return GetChallengeEmailContentsResponseDto.of(title, contents);
    }
}
