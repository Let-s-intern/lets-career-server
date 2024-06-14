package org.letscareer.letscareer.domain.attendance.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AttendanceErrorCode implements ErrorCode {
    ATTENDANCE_NOT_FOUND(HttpStatus.NOT_FOUND, "출석 정보를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
