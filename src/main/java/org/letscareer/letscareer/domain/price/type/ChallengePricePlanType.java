package org.letscareer.letscareer.domain.price.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ChallengePricePlanType implements EnumField {
    BASIC(1, "베이직"),
    STANDARD(2, "스탠다드"),
    PREMIUM(3, "프리미엄");

    private final Integer code;
    private final String desc;
}
