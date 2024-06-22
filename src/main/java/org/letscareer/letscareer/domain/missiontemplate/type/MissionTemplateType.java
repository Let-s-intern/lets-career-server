package org.letscareer.letscareer.domain.missiontemplate.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MissionTemplateType implements EnumField {

    EXPERIENCE(1, "경험정리"),
    ;

    private final Integer code;
    private final String desc;
}
