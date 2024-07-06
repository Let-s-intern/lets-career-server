package org.letscareer.letscareer.domain.user.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.e.EnumField;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserGrade implements EnumField {
    FIRST(1, "1학년"),
    SECOND(2, "2학년"),
    THIRD(3, "3학년"),
    FOURTH(4, "4학년"),
    ETC(5, "5학년 이상"),
    GRADUATE(6, "졸업생");

    private final Integer code;
    private final String desc;
}
