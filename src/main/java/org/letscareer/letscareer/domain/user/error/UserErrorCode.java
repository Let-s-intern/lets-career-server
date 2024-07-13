package org.letscareer.letscareer.domain.user.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum UserErrorCode implements ErrorCode {
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "이메일 형식이 잘못되었습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호 형식이 잘못되었습니다."),
    INVALID_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "전화번호 형식이 잘못되었습니다."),
    IS_NOT_ADMIN(HttpStatus.UNAUTHORIZED, "API 접근 권한이 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    USER_PHONE_NUMBER_CONFLICT(HttpStatus.CONFLICT, "이미 가입된 전화번호입니다."),
    USER_EMAIL_CONFLICT(HttpStatus.CONFLICT, "이미 가입된 이메일입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
