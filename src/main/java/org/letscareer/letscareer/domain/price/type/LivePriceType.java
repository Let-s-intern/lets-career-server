package org.letscareer.letscareer.domain.price.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum LivePriceType implements EnumField {
    FREE(1, "무료"),
    CHARGE(2, "보증금");

    private final Integer code;
    private final String desc;
}

