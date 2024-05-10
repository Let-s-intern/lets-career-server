package org.letscareer.letscareer.global.error.exception;

import lombok.Getter;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.letscareer.letscareer.global.error.GlobalErrorCode;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}