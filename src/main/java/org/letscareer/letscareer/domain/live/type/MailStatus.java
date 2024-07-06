package org.letscareer.letscareer.domain.live.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum MailStatus implements EnumField {
    REMIND(1, "리마인드"),
    REVIEW(2, "리뷰"),
    DONE(3, "완료");

    private final Integer code;
    private final String desc;
}
