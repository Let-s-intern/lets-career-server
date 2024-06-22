package org.letscareer.letscareer.domain.vod.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum VodErrorCode implements ErrorCode {
    VOD_NOT_FOUND(HttpStatus.NOT_FOUND, "vod 프로그램을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
