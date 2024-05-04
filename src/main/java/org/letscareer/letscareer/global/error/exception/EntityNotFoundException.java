package org.letscareer.letscareer.global.error.exception;

import org.letscareer.letscareer.global.error.GlobalErrorCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException() {
        super(GlobalErrorCode.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(GlobalErrorCode errorCode) {
        super(errorCode);
    }
}

