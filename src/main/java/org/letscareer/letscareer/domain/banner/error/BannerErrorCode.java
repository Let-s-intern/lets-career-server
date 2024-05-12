package org.letscareer.letscareer.domain.banner.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum BannerErrorCode implements ErrorCode {

    BANNER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 배너입니다");

    private final HttpStatus httpStatus;
    private final String message;
}
