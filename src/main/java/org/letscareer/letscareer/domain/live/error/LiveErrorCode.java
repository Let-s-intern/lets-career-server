package org.letscareer.letscareer.domain.live.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum LiveErrorCode implements ErrorCode {
    LIVE_NOT_FOUND(HttpStatus.NOT_FOUND, "Live program을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
