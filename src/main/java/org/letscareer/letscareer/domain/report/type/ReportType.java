package org.letscareer.letscareer.domain.report.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReportType implements EnumField {
    RESUME(1, "이력서"),
    PERSONAL_STATEMENT(2, "자기소개서"),
    PORTFOLIO(3, "포트폴리오");

    private final Integer code;
    private final String desc;
}
