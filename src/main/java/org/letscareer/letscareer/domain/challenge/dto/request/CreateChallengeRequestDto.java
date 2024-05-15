package org.letscareer.letscareer.domain.challenge.dto.request;

import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.classification.dto.request.CreateChallengeClassificationRequestDto;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.price.dto.request.CreateChallengePriceRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public record CreateChallengeRequestDto(
        String title,
        String shortDesc,
        String desc,
        Integer participationCount,
        String thumbnail,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline,
        String chatLink,
        String chatPassword,
        ChallengeType challengeType,
        List<CreateChallengeClassificationRequestDto> programTypeInfo,
        List<CreateChallengePriceRequestDto> priceInfo,
        List<CreateProgramFaqRequestDto> faqInfo
) {
}
