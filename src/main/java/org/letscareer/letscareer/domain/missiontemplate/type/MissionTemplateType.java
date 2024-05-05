package org.letscareer.letscareer.domain.missiontemplate.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MissionTemplateType implements EnumField {
    GENERAL(1, "일반"),
    REWARD(2, "제한 컨텐츠 제공"),
    REFUND(3, "보증금 반환");

    private final Integer code;
    private final String desc;
}
