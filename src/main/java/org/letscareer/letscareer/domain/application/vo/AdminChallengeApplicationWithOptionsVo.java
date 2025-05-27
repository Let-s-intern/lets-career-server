package org.letscareer.letscareer.domain.application.vo;

public record AdminChallengeApplicationWithOptionsVo(
        AdminChallengeApplicationVo application,
        int optionPriceSum,
        int optionDiscountPriceSum
) {
}
