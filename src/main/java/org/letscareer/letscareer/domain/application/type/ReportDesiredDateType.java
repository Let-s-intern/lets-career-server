package org.letscareer.letscareer.domain.application.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReportDesiredDateType implements EnumField {
    DESIRED_DATE_1(1, "1"),
    DESIRED_DATE_2(2, "2"),
    DESIRED_DATE_3(3, "3"),
    DESIRED_DATE_ADMIN(4, "어드민");

    private final Integer code;
    private final String desc;
}
