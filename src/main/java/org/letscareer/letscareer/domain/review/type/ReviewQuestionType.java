package org.letscareer.letscareer.domain.review.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReviewQuestionType implements EnumField {
    GOOD_POINT(1, "좋았던 점"),
    BAD_POINT(2, "아쉬웠던 점"),
    GOAL(3, "목표"),
    GOAL_RESULT(4, "목표 달성 여부"),
    WORRY(5, "고민"),
    WORRY_RESULT(6, "고민 해결 여부");

    private final Integer code;
    private final String desc;
}
