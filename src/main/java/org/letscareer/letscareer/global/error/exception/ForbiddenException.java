package org.letscareer.letscareer.global.error.exception;

import org.letscareer.letscareer.global.error.ErrorCode;
import org.letscareer.letscareer.global.error.GlobalErrorCode;

public class ForbiddenException extends BusinessException {
    public ForbiddenException() {
        super(GlobalErrorCode.FORBIDDEN);
    }

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}

