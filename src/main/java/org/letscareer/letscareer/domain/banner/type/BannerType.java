package org.letscareer.letscareer.domain.banner.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BannerType {
    MAIN(1, "메인"),
    PROGRAM(2, "프로그램"),
    LINE(3, "띠"),
    POPUP(4, "팝업");

    private final Integer code;
    private final String desc;
}
