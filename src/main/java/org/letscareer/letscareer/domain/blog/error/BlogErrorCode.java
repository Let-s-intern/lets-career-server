package org.letscareer.letscareer.domain.blog.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum BlogErrorCode implements ErrorCode {
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 블로그입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
