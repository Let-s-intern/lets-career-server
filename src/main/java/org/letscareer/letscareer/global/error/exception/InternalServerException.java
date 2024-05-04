package org.letscareer.letscareer.global.error.exception;

import org.letscareer.letscareer.global.error.GlobalErrorCode;

public class InternalServerException extends BusinessException {
    public InternalServerException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}

