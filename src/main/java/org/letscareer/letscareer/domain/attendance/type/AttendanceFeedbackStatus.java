package org.letscareer.letscareer.domain.attendance.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum AttendanceFeedbackStatus implements EnumField {
    WAITING(1, "진행 전"),
    IN_PROGRESS(2, "진행중"),
    COMPLETED(3, "진행 완료"),
    CONFIRMED(4, "확인 완료");

    private final Integer code;
    private final String desc;
}
