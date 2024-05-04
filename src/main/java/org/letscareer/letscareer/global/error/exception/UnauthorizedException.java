package org.letscareer.letscareer.global.error.exception;

import org.letscareer.letscareer.global.error.GlobalErrorCode;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException() {
        super(GlobalErrorCode.UNAUTHORIZED);
    }

    public UnauthorizedException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}

