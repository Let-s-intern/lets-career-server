package org.letscareer.letscareer.domain.challlengenotice.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ChallengeNoticeType implements EnumField {
    ESSENTIAL(1, "essential"),
    ADDITIONAL(2, "additional");

    private final Integer code;
    private final String desc;
}
