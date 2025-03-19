package org.letscareer.letscareer.domain.coupon.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CouponType implements EnumField {
    PARTNERSHIP(1, "제휴"),
    EVENT(2, "이벤트"),
    GRADE(3, "등급별할인"),
    CHALLENGE(4, "챌린지");

    private final Integer code;
    private final String desc;
}

