package org.letscareer.letscareer.domain.attendance.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum AttendanceResult implements EnumField {
    WAITING(1, "확인중"),
    PASS(2, "확인 완료"),
    WRONG(3, "반려");

    private final Integer code;
    private final String desc;
}
