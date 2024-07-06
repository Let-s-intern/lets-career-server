package org.letscareer.letscareer.domain.price.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.e.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ChallengeParticipationType implements EnumField {
    LIVE(1, "라이브"),
    FREE(2, "자율일정");

    private final Integer code;
    private final String desc;
}
