package org.letscareer.letscareer.domain.notice.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum NoticeType implements EnumField {
    ESSENTIAL(1, "essential"),
    ADDITIONAL(2, "additional");

    private final Integer code;
    private final String desc;
}
