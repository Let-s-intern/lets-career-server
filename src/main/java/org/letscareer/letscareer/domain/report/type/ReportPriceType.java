package org.letscareer.letscareer.domain.report.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReportPriceType implements EnumField {
    BASIC(1, "기본"),
    PREMIUM(2, "프리미엄");

    private final Integer code;
    private final String desc;
}
