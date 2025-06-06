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
    REPORT_NOT_FOUND(HttpStatus.NOT_FOUND, "진단서 프로그램을 찾을 수 없습니다."),
    REPORT_FEEDBACK_APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "1:1 피드백 신청서를 찾을 수 없습니다."),
    REPORT_CONFLICT_VISIBLE_DATE(HttpStatus.CONFLICT, "이미 노출된 진단서 프로그램이 있습니다."),
    REPORT_CANCEL_NOT_AVAILABLE(HttpStatus.BAD_REQUEST, "환불이 불가능합니다."),
    DUPLICATE_REPORT_APPLICATION(HttpStatus.CONFLICT, "이미 신청된 진단서 입니다."),
    REPORT_CANNOT_UPDATED(HttpStatus.UNAUTHORIZED, "진단 신청서 수정 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
