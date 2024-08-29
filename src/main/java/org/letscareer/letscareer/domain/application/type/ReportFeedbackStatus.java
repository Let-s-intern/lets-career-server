package org.letscareer.letscareer.domain.application.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReportFeedbackStatus implements EnumField {
    APPLIED(1, "신청완료"),
    PENDING(2, "확인중"),
    CONFIRMED(3, "일정확정"),
    COMPLETED(4, "진행완료");

    private final Integer code;
    private final String desc;
}
