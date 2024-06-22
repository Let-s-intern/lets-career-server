package org.letscareer.letscareer.global.error.exception;

import org.letscareer.letscareer.global.error.ErrorCode;

public class InternalServerException extends BusinessException {
    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}

