package org.letscareer.letscareer.domain.banner.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BannerType implements EnumField {
    MAIN(1, "메인"),
    PROGRAM(2, "프로그램"),
    LINE(3, "띠"),
    POPUP(4, "팝업"),
    MAIN_BOTTOM(5, "메인 하단");

    private final Integer code;
    private final String desc;

    public static class Values {
        public static final String MAIN = "MAIN";
        public static final String PROGRAM = "PROGRAM";
        public static final String LINE = "LINE";
        public static final String POPUP = "POPUP";
    }
}
