package org.letscareer.letscareer.domain.price.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ChallengePriceErrorCode implements ErrorCode {
    CHALLENGE_PRICE_NOT_FOUND(HttpStatus.NOT_FOUND, "challenge 가격 정책을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
