package org.letscareer.letscareer.domain.application.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TossProgramType implements EnumField {
    CHALLENGE_TOSS(1, "CHALLENGE"),
    LIVE_TOSS(2, "LIVE"),
    VOD(3, "VOD");

    private final Integer code;
    private final String desc;

    public static TossProgramType parseTossProgramType(ProgramType programType) {
        return Arrays.stream(values())
                .filter(tossProgramType -> tossProgramType.desc.equals(programType.getDesc()))
                .findFirst()
                .orElseThrow();
    }
}
