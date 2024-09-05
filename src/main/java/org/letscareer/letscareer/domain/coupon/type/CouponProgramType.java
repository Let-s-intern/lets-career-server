package org.letscareer.letscareer.domain.coupon.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CouponProgramType implements EnumField {
    ALL(1, "전체"),
    CHALLENGE(2, "챌린지"),
    LIVE(3, "live"),
    VOD(4, "vod"),
    REPORT(5, "서류진단");

    private final Integer code;
    private final String desc;
}

