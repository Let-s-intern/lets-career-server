package org.letscareer.letscareer.domain.challenge.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ChallengeType implements EnumField {
    CAREER_START(1, "커리어 스타트"),
    DOCUMENT_PREPARATION(2, " 서류준비"),
    MEETING_PREPARATION(3, "면접준비"),
    ETC(4, "기타"),
    PERSONAL_STATEMENT(5, "자기소개서"),
    PORTFOLIO(6, "포트폴리오");

    private final Integer code;
    private final String desc;
}
