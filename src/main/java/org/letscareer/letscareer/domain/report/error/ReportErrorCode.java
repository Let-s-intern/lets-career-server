package org.letscareer.letscareer.domain.report.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReportErrorCode implements ErrorCode {
    REPORT_APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "진단 신청서를 찾을 수 없습니다."),
    REPORT_NOT_FOUND(HttpStatus.NOT_FOUND, "진단서 프로그램을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
