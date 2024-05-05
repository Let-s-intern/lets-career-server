package org.letscareer.letscareer.domain.coupon.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CouponProgramType implements EnumField {
    ALL(1, "전체"),
    CHALLENGE(2, "챌린지"),
    LIVE(3, "live"),
    VOD(4, "vod");

    private final Integer code;
    private final String desc;
}

