package org.letscareer.letscareer.domain.challengeoption.vo;

public record ChallengeOptionVo(
        Long challengeOptionId,
        String title,
        Integer price,
        Integer discountPrice
) {
}
