package org.letscareer.letscareer.global.error.exception;

import org.letscareer.letscareer.global.error.GlobalErrorCode;

public class InvalidValueException extends BusinessException {
    public InvalidValueException() {
        super(GlobalErrorCode.BAD_REQUEST);
    }

    public InvalidValueException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}
