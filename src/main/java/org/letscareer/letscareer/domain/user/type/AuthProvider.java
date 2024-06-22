package org.letscareer.letscareer.domain.user.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthProvider implements EnumField {
    KAKAO(1, "카카오톡"),
    NAVER(2, "네이버"),
    GOOGLE(3, "구글"),
    SERVICE(4, "서비스");

    private final Integer code;
    private final String desc;
}
