package org.letscareer.letscareer.domain.pg.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum PgType implements EnumField {
    TOSS(1, "토스");

    private final Integer code;
    private final String desc;
}
