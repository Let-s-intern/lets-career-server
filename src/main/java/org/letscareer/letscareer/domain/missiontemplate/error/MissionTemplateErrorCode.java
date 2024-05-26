package org.letscareer.letscareer.domain.missiontemplate.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MissionTemplateErrorCode implements ErrorCode {

    MISSION_TEMPLATE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 미션 템플릿입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
