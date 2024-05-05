package org.letscareer.letscareer.domain.contents.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.EnumField;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ContentsType implements EnumField {
    NONE(0, "없음"),
    EXPERIENCE(1, "경험정리"),
    JOB(2, "직무탐색"),
    CONCEPT(3, "컨셉잡기"),
    DOCUMENT(4, "서류작성"),
    RECRUITMENT(5, "공고분석"),
    APPLY(6, "지원하기");

    private final Integer code;
    private final String desc;

}
