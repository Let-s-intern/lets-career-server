package org.letscareer.letscareer.global.error.handler;

import lombok.extern.slf4j.Slf4j;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.letscareer.letscareer.global.error.GlobalErrorCode;
import org.letscareer.letscareer.global.error.entity.ErrorResponse;
import org.letscareer.letscareer.global.error.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Valid & Validated annotation의 binding error를 handling합니다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(">>> handle: MethodArgumentNotValidException ", e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            sb.append("[" + fieldName + "] " + errorMessage);
        }
        final ErrorResponse errorBaseResponse = ErrorResponse.validOf(GlobalErrorCode.BAD_REQUEST, sb.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBaseResponse);
    }

    /**
     * ModelAttribute annotation의 binding error를 handling합니다.
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.error(">>> handle: BindException ", e);
        final ErrorResponse errorBaseResponse = ErrorResponse.of(GlobalErrorCode.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBaseResponse);
    }

    /**
     * RequestParam annotation의 binding error를 handling합니다.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error(">>> handle: MethodArgumentTypeMismatchException ", e);
        final ErrorResponse errorBaseResponse = ErrorResponse.of(GlobalErrorCode.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBaseResponse);
    }

    /**
     * 지원하지 않는 HTTP method로 요청 시 발생하는 error를 handling합니다.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(">>> handle: HttpRequestMethodNotSupportedException ", e);
        final ErrorResponse errorBaseResponse = ErrorResponse.of(GlobalErrorCode.METHOD_NOT_ALLOWED);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorBaseResponse);
    }

    /**
     * BusinessException을 handling합니다.
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error(">>> handle: BusinessException ", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse errorBaseResponse = ErrorResponse.of(errorCode);
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorBaseResponse);
    }

    /**
     * 위에서 정의한 Exception을 제외한 모든 예외를 handling합니다.
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error(">>> handle: Exception ", e);
        final ErrorResponse errorBaseResponse = ErrorResponse.of(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBaseResponse);
    }
}
