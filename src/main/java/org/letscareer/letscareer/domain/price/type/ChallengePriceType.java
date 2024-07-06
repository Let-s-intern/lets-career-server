package org.letscareer.letscareer.domain.price.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.e.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ChallengePriceType implements EnumField {
    CHARGE(1, "이용료"),
    REFUND(2, "보증금");

    private final Integer code;
    private final String desc;
}

