package org.letscareer.letscareer.domain.challenge.dto.request;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.classification.dto.request.CreateChallengeClassificationRequestDto;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.price.dto.request.CreateChallengePriceRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public record CreateChallengeRequestDto(
        @NotNull
        String title,
        String shortDesc,
        String desc,
        String criticalNotice,
        Integer participationCount,
        @NotNull
        String thumbnail,
        @NotNull
        LocalDateTime startDate,
        @NotNull
        LocalDateTime endDate,
        @NotNull
        LocalDateTime beginning,
        @NotNull
        LocalDateTime deadline,
        @NotNull
        String chatLink,
        @NotNull
        String chatPassword,
        @NotNull
        ChallengeType challengeType,
        @NotNull
        List<CreateChallengeClassificationRequestDto> programTypeInfo,
        @NotNull
        List<CreateChallengePriceRequestDto> priceInfo,
        @NotNull
        List<CreateProgramFaqRequestDto> faqInfo
) {
}
