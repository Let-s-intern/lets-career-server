package org.letscareer.letscareer.domain.coupon.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CouponErrorCode implements ErrorCode {
    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 쿠폰입니다."),
    COUPON_NOT_AVAILABLE_DATE(HttpStatus.BAD_REQUEST, "쿠폰 사용이 불가능한 기간입니다."),
    COUPON_NOT_AVAILABLE_PROGRAM_TYPE(HttpStatus.BAD_REQUEST, "쿠폰 사용이 불가능한 프로그램입니다."),
    COUPON_NOT_AVAILABLE_REMAIN_TIME(HttpStatus.BAD_REQUEST, "더 이상 사용할 수 없는 쿠폰입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
