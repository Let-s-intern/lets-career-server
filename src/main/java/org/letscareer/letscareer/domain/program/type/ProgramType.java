package org.letscareer.letscareer.domain.program.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ProgramType implements EnumField {
    CHALLENGE(1, "challenge"),
    LIVE(2, "live"),
    VOD(3, "vod"),
    REPORT(4, "report");

    private final Integer code;
    private final String desc;
}
