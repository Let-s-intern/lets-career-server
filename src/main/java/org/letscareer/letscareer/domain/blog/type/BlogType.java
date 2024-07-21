package org.letscareer.letscareer.domain.blog.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.common.utils.enm.EnumField;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum BlogType implements EnumField {
    JOB_PREPARATION_TIPS(1, "취준 TIP"),
    PROGRAM_REVIEWS(2, "프로그램 후기"),
    JOB_SUCCESS_STORIES(3, "취뽀 후기"),
    WORK_EXPERIENCES(4, "근무 후기"),
    JUNIOR_STORIES(5, "주니어 이야기"),
    LETSCAREER_NEWS(6, "렛츠커리어 소식");

    private final Integer code;
    private final String desc;
}
