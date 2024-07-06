package org.letscareer.letscareer.domain.classification.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.e.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ProgramClassification implements EnumField {
    CAREER_SEARCH(1, "커리어서치"),
    DOCUMENT_PREPARATION(2, " 서류준비"),
    MEETING_PREPARATION(3, "면접준비"),
    PASS(4, "합격 후 성장");

    private final Integer code;
    private final String desc;
}
