package org.letscareer.letscareer.global.error.exception;

import org.letscareer.letscareer.global.error.ErrorCode;
import org.letscareer.letscareer.global.error.GlobalErrorCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException() {
        super(GlobalErrorCode.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}

