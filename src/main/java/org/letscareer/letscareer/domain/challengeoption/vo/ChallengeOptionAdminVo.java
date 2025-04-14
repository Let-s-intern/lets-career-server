package org.letscareer.letscareer.domain.challengeoption.vo;

public record ChallengeOptionAdminVo(
        Long challengeOptionId,
        String title,
        String code,
        Integer price,
        Integer discountPrice
) {
}
