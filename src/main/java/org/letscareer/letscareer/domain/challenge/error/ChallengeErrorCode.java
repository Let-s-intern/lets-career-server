package org.letscareer.letscareer.domain.challenge.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ChallengeErrorCode implements ErrorCode {
    CHALLENGE_NOT_FOUND(HttpStatus.NOT_FOUND, "엔티티를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
