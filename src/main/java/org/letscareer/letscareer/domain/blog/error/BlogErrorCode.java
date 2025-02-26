package org.letscareer.letscareer.domain.blog.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum BlogErrorCode implements ErrorCode {
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 블로그입니다."),
    HASHTAG_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 해시태그입니다."),
    HASHTAG_CANNOT_DELETED(HttpStatus.BAD_REQUEST, "삭제가 불가능한 해시태그입니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
