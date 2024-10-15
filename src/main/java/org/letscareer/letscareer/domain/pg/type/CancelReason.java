package org.letscareer.letscareer.domain.pg.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CancelReason implements EnumField {
    CUSTOMER(1, "고객이 취소를 원함"),
    PAYBACK(2, "챌린지 페이백");

    private final Integer code;
    private final String desc;
}
