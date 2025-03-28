package org.letscareer.letscareer.domain.review.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReviewErrorCode implements ErrorCode {
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "리뷰를 찾을 수 없습니다"),
    REVIEW_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "리뷰 아이템을 찾을 수 없습니다"),
    BLOG_REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "블로그 리뷰를 찾을 수 없습니다"),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 작성된 리뷰가 존재합니다");

    private final HttpStatus httpStatus;
    private final String message;
}
