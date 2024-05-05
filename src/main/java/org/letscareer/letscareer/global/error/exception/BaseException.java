package org.letscareer.letscareer.global.error.exception;

import lombok.Getter;
import org.letscareer.letscareer.global.error.GlobalErrorCode;

@Getter
public class BaseException extends RuntimeException {
    private GlobalErrorCode errorCode;
}
