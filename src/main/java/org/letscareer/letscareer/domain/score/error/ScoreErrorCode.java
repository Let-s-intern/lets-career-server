package org.letscareer.letscareer.domain.score.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ScoreErrorCode implements ErrorCode {
    ATTENDANCE_SCORE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 참가자 입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
