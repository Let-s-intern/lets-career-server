package org.letscareer.letscareer.domain.curation.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CurationLocationType implements EnumField {
    UNDER_BANNER(1, "배너 하단"),
    UNDER_REVIEW(2, "후기 하단"),
    UNDER_BLOG(3, "블로그 하단");

    private final Integer code;
    private final String desc;
}
