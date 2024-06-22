package org.letscareer.letscareer.domain.price.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ChallengeUserType implements EnumField {
    BASIC(1, "베이직"),
    PREMIUM(2, "프로미엄");

    private final Integer code;
    private final String desc;
}
