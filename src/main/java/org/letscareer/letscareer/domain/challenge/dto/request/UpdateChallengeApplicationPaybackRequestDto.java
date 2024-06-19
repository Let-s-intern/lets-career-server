package org.letscareer.letscareer.domain.challenge.dto.request;

public record UpdateChallengeApplicationPaybackRequestDto(
        Integer adminScore,
        Boolean isRefunded
) {
}
