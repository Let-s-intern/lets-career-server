package org.letscareer.letscareer.domain.attendance.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum AttendanceStatus implements EnumField {
    PRESENT(1, "정상 제출"),
    UPDATED(2, "다시 제출"),
    LATE(3, "지각 제출"),
    ABSENT(4, "미제출");

    private final Integer code;
    private final String desc;
}
