package org.letscareer.letscareer.global.error.toss;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Builder(access = AccessLevel.PRIVATE)
public class TossErrorCode implements ErrorCode {
    private HttpStatus code;
    private String message;

    @Override
    public HttpStatus getHttpStatus() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public static TossErrorCode of(Integer code, String message) {
        HttpStatus httpStatus = getHttpStatus(code);
        return TossErrorCode.builder()
                .code(httpStatus)
                .message(message)
                .build();
    }

    private static HttpStatus getHttpStatus(Integer code) {
        if (code.equals(HttpStatus.BAD_REQUEST.value()))
            return HttpStatus.BAD_REQUEST;
        else if (code.equals(HttpStatus.UNAUTHORIZED.value()))
            return HttpStatus.UNAUTHORIZED;
        else if (code.equals(HttpStatus.FORBIDDEN.value()))
            return HttpStatus.FORBIDDEN;
        else if (code.equals(HttpStatus.NOT_FOUND.value()))
            return HttpStatus.BAD_REQUEST;
        else if (code.equals(HttpStatus.METHOD_NOT_ALLOWED.value()))
            return HttpStatus.METHOD_NOT_ALLOWED;
        else if (code.equals(HttpStatus.CONFLICT.value()))
            return HttpStatus.CONFLICT;
        else
            return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
