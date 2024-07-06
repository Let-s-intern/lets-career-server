package org.letscareer.letscareer.domain.contents.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.e.EnumField;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ContentsType implements EnumField {
    ESSENTIAL(1, "필수"),
    ADDITIONAL(2, "추가");

    private final Integer code;
    private final String desc;
}
