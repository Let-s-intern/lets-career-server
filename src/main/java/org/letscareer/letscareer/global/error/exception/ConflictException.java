package org.letscareer.letscareer.global.error.exception;

import org.letscareer.letscareer.global.error.GlobalErrorCode;

public class ConflictException extends BusinessException {
    public ConflictException() {
        super(GlobalErrorCode.CONFLICT);
    }

    public ConflictException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}


