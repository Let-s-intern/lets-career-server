package org.letscareer.letscareer.domain.review.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReviewProgramType implements EnumField {
    CHALLENGE_REVIEW(1, "challenge"),
    LIVE_REVIEW(2, "live"),
    VOD_REVIEW(3, "vod"),
    REPORT_REVIEW(4, "report"),
    MISSION_REVIEW(5, "mission");

    private final Integer code;
    private final String desc;
}
