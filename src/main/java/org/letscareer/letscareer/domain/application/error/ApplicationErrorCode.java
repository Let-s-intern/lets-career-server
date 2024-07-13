package org.letscareer.letscareer.domain.application.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ApplicationErrorCode implements ErrorCode {
    INVALID_APPLICATION_TIME(HttpStatus.BAD_REQUEST, "신청이 마감된 프로그램입니다."),
    APPLICATION_ALREADY_CANCELED(HttpStatus.CONFLICT, "이미 취소된 신청 내역입니다."),
    APPLICATION_CANNOT_CANCELED(HttpStatus.BAD_REQUEST, "환불이 불가능한 기간입니다."),
    CONFLICT_APPLICATION(HttpStatus.CONFLICT, "이미 존재하는 신청 내역입니다."),
    LIVE_BAD_REQUEST(HttpStatus.BAD_REQUEST, "지원동기를 입력해주세요."),
    APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 신청 내역입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
