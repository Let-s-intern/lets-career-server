package org.letscareer.letscareer.domain.challenge.mapper;

import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeDetailResponseDto;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeDetailVo;
import org.letscareer.letscareer.domain.classification.vo.ChallengeClassificationDetailVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeMapper {

    public GetChallengeDetailResponseDto createChallengeDetailResponseDto(ChallengeDetailVo challengeInfo,
                                                                          List<ChallengeClassificationDetailVo> classificationInfo,
                                                                          List<ChallengePriceDetailVo> priceInfo,
                                                                          List<FaqDetailVo> faqInfo) {
        return GetChallengeDetailResponseDto.of(challengeInfo, classificationInfo, priceInfo, faqInfo);
    }
}
