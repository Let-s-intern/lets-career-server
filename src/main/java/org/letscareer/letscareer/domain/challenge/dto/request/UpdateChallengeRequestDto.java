package org.letscareer.letscareer.domain.challenge.dto.request;

import org.letscareer.letscareer.domain.admincalssification.request.CreateChallengeAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.classification.dto.request.CreateChallengeClassificationRequestDto;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.price.dto.request.CreateChallengePriceRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateChallengeRequestDto(
        String title,
        String shortDesc,
        String desc,
        String criticalNotice,
        Integer participationCount,
        String thumbnail,
        String desktopThumbnail,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime beginning,
        LocalDateTime deadline,
        String chatLink,
        String chatPassword,
        ChallengeType challengeType,
        Boolean isVisible,
        List<CreateChallengeClassificationRequestDto> programTypeInfo,
        List<CreateChallengeAdminClassificationRequestDto> adminProgramTypeInfo,
        List<CreateChallengePriceRequestDto> priceInfo,
        List<CreateProgramFaqRequestDto> faqInfo
) {
}
