package org.letscareer.letscareer.global.error.toss;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.letscareer.letscareer.global.error.exception.*;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

import static org.letscareer.letscareer.global.error.GlobalErrorCode.TOSS_ERROR_FORMAT;

public class TossErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        TossErrorResponse message = parseTossErrorResponse(response);
        return getExceptionWithMessageForErrorCode(message, response.status());
    }

    private TossErrorResponse parseTossErrorResponse(Response response) {
        TossErrorResponse message;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, TossErrorResponse.class);
        } catch (IOException e) {
            throw new BusinessException(TOSS_ERROR_FORMAT);
        }
        return message;
    }

    private Exception getExceptionWithMessageForErrorCode(TossErrorResponse message, Integer status) {
        TossErrorCode tossErrorCode = TossErrorCode.of(status, message.message());
        if (HttpStatus.BAD_REQUEST.equals(tossErrorCode.getHttpStatus()))
            return new InvalidValueException(tossErrorCode);
        else if (HttpStatus.UNAUTHORIZED.equals(tossErrorCode.getHttpStatus()))
            return new UnauthorizedException(tossErrorCode);
        else if (HttpStatus.FORBIDDEN.equals(tossErrorCode.getHttpStatus()))
            return new ForbiddenException(tossErrorCode);
        else if (HttpStatus.NOT_FOUND.equals(tossErrorCode.getHttpStatus()))
            return new EntityNotFoundException(tossErrorCode);
        else if (HttpStatus.METHOD_NOT_ALLOWED.equals(tossErrorCode.getHttpStatus()))
            return new MethodNotAllowedException(tossErrorCode);
        else if (HttpStatus.CONFLICT.equals(tossErrorCode.getHttpStatus()))
            return new ConflictException(tossErrorCode);
        else
            return new InternalServerException(tossErrorCode);
    }
}
