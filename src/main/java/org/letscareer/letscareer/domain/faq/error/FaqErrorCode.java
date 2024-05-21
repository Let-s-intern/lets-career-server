package org.letscareer.letscareer.domain.faq.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum FaqErrorCode implements ErrorCode {
    FAQ_NOT_FOUND(HttpStatus.NOT_FOUND, "faq를 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
