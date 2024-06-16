package org.letscareer.letscareer.domain.attendance.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AttendanceErrorCode implements ErrorCode {
    ATTENDANCE_NOT_AVAILABLE_DATE(HttpStatus.BAD_REQUEST, "출석 제출이 불가능한 기간입니다"),
    CONFLICT_ATTENDANCE(HttpStatus.CONFLICT, "이미 제출한 출석 내역이 존재합니다"),
    ATTENDANCE_NOT_FOUND(HttpStatus.NOT_FOUND, "출석 정보를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
