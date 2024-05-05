package org.letscareer.letscareer.domain.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserGrade implements EnumField {
    GRADUATE(0, "졸업생"),
    FIRST(1, "1학년"),
    SECOND(2, "2학년"),
    THIRD(3, "3학년"),
    FOURTH(4, "4학년"),
    ETC(5, "5학년 이상");

    private final Integer code;
    private final String desc;
}
