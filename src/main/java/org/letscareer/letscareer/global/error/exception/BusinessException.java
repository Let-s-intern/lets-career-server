package org.letscareer.letscareer.global.error.exception;

import lombok.Getter;
import org.letscareer.letscareer.global.error.GlobalErrorCode;

@Getter
public class BusinessException extends RuntimeException {
    private final GlobalErrorCode errorCode;

    public BusinessException(String message, GlobalErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(GlobalErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}