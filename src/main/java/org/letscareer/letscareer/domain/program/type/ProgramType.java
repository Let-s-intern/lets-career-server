package org.letscareer.letscareer.domain.program.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ProgramType implements EnumField {
    CHALLENGE(1, "CHALLENGE"),
    LIVE(2, "LIVE"),
    VOD(3, "VOD");

    private final Integer code;
    private final String desc;
}
