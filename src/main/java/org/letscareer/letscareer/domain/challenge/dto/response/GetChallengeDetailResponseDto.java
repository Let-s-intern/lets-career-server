package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeDetailVo;
import org.letscareer.letscareer.domain.classification.vo.ChallengeClassificationDetailVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeDetailResponseDto(
        ChallengeDetailVo challengeInfo,
        List<ChallengeClassificationDetailVo> classificationInfo,
        List<ChallengePriceDetailVo> priceInfo,
        List<FaqDetailVo> faqInfo
) {
    public static GetChallengeDetailResponseDto of(ChallengeDetailVo challengeInfo,
                                                   List<ChallengeClassificationDetailVo> classificationInfo,
                                                   List<ChallengePriceDetailVo> priceInfo,
                                                   List<FaqDetailVo> faqInfo) {
        return GetChallengeDetailResponseDto.builder()
                .challengeInfo(challengeInfo)
                .classificationInfo(classificationInfo)
                .priceInfo(priceInfo)
                .faqInfo(faqInfo)
                .build();
    }
}
