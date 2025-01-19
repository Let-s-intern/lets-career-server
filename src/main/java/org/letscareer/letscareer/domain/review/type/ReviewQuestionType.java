package org.letscareer.letscareer.domain.review.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReviewQuestionType implements EnumField {
    GOAL(1, "목표"),
    GOAL_RESULT(2, "목표 달성 여부"),
    WORRY(3, "고민"),
    WORRY_RESULT(4, "고민 해결 여부");

    private final Integer code;
    private final String desc;
}
