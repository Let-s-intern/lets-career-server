package org.letscareer.letscareer.domain.challengementor.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ChallengeMentorErrorCode implements ErrorCode {
    CHALLENGE_MENTOR_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 챌린지 멘토입니다."),
    NOT_CHALLENGE_MENTOR(HttpStatus.FORBIDDEN, "챌린지 멘토가 아닙니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
