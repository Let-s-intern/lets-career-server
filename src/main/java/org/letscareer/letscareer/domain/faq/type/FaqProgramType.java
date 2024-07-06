package org.letscareer.letscareer.domain.faq.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.e.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum FaqProgramType implements EnumField {
    CHALLENGE(1, "챌린지"),
    LIVE(2, "live"),
    VOD(3, "vod");

    private final Integer code;
    private final String desc;
}
