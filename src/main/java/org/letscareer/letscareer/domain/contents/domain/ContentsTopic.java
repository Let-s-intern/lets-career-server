package org.letscareer.letscareer.domain.contents.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ContentsTopic implements EnumField {
    ESSENTIAL(1, "필수"),
    ADDITIONAL(2, "추가"),
    LIMITED(3, "제한");

    private final Integer code;
    private final String desc;
}
