package org.letscareer.letscareer.domain.mission.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum MissionStatusType implements EnumField {
    WAITING(1, "대기"),
    CHECK_DONE(2, "확인 완료"),
    REFUND_DONE(3, "환급 완료");

    private final Integer code;
    private final String desc;
}
