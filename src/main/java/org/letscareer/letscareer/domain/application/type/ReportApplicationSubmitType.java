package org.letscareer.letscareer.domain.application.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReportApplicationSubmitType implements EnumField {
    NORMAL(1, "동시 제출"),
    LATE(2, "후 제출");

    private final Integer code;
    private final String desc;
}
