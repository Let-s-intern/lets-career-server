package org.letscareer.letscareer.domain.user.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountType implements EnumField {
    KB(1, "국민"),
    HANA(2, "하나"),
    WOORI(3, "우리"),
    SHINHAN(4, "신한"),
    NH(5, "농협"),
    SH(6, "수협"),
    IBK(7, "IBK 기업"),
    MG(8, "새마을금고"),
    KAKAO(9, "카카오뱅크"),
    TOSS(10, "토스뱅크");

    private final Integer code;
    private final String desc;
}
