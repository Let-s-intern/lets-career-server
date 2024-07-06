package org.letscareer.letscareer.domain.live.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.e.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ProgressType implements EnumField {
    ALL(1, "온/오프라인"),
    ONLINE(2, "온라인"),
    OFFLINE(3, "오프라인");

    private final Integer code;
    private final String desc;
}
