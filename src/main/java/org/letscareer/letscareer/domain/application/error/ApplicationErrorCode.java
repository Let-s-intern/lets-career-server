package org.letscareer.letscareer.domain.application.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ApplicationErrorCode implements ErrorCode {
    CONFLICT_APPLICATION(HttpStatus.CONFLICT, "이미 존재하는 신청 내역입니다."),
    LIVE_BAD_REQUEST(HttpStatus.BAD_REQUEST, "지원동기를 입력해주세요");

    private final HttpStatus httpStatus;
    private final String message;
}
