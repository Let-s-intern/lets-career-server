package org.letscareer.letscareer.domain.curation.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CurationItemProgramType implements EnumField {
    ETC(1, "기타"),
    CHALLENGE(2, "챌린지"),
    LIVE(3, "라이브"),
    VOD(4, "VOD"),
    REPORT(5, "서류진단"),
    BLOG(6, "블로그");

    private final Integer code;
    private final String desc;
}
