package org.letscareer.letscareer.domain.file.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum FileType {
    BANNER_MAIN(1, "banner/main/"),
    BANNER_PROGRAM(2, "banner/program/"),
    BANNER_POPUP(3, "banner/popup/"),
    CHALLENGE(4, "program/challenge/"),
    LIVE(5, "program/live/"),
    VOD(6, "program/vod/"),
    BLOG(7, "blog/"),
    REPORT(8, "report/"),
    BLOG_REVIEW(9, "review/blog/");

    private final Integer code;
    private final String desc;
}
