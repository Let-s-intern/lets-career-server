package org.letscareer.letscareer.domain.application.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReportApplicationStatus implements EnumField {
    APPLIED(1, "신청완료"),
    REPORTING(2, "진단중"),
    REPORTED(3, "진단서 업로드"),
    COMPLETED(4, "진단완료");

    private final Integer code;
    private final String desc;
}
