package org.letscareer.letscareer.domain.user.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserRole implements EnumField {
    ADMIN(1, "관리자"),
    USER(2, "사용자");

    private final Integer code;
    private final String desc;
}
