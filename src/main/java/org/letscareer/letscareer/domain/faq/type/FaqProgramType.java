package org.letscareer.letscareer.domain.faq.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum FaqProgramType implements EnumField {
    CHALLENGE(1, "챌린지"),
    LIVE(2, "라이브 클래스"),
    VOD(3, "VOD"),
    REPORT(4, "서류진단");

    private final Integer code;
    private final String desc;
}
