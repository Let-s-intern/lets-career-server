package org.letscareer.letscareer.domain.admincalssification.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ProgramAdminClassification implements EnumField {
    B2C(1, "B2C"),
    B2B(2, " B2C");

    private final Integer code;
    private final String desc;
}
