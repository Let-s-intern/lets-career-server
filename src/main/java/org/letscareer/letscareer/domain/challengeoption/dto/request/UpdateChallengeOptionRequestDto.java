package org.letscareer.letscareer.domain.challengeoption.dto.request;

public record UpdateChallengeOptionRequestDto(
        String title,
        String code,
        Integer price,
        Integer discountPrice,
        Boolean isFeedback
) {
}
