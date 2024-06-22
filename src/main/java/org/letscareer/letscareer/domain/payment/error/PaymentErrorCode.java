package org.letscareer.letscareer.domain.payment.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum PaymentErrorCode implements ErrorCode {
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 신청 내역입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
