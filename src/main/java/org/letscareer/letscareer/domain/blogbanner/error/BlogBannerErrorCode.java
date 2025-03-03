package org.letscareer.letscareer.domain.blogbanner.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum BlogBannerErrorCode implements ErrorCode {
    BLOG_BANNER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 블로그 배너입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
