package org.letscareer.letscareer.domain.challengeoption.dto.request;

public record CreateChallengeOptionRequestDto(
        String title,
        String code,
        Integer price,
        Integer discountPrice
) {
}
