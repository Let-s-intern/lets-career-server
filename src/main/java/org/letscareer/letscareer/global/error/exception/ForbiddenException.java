package org.letscareer.letscareer.global.error.exception;

import org.letscareer.letscareer.global.error.GlobalErrorCode;

public class ForbiddenException extends BusinessException {
    public ForbiddenException() {
        super(GlobalErrorCode.FORBIDDEN);
    }

    public ForbiddenException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}

