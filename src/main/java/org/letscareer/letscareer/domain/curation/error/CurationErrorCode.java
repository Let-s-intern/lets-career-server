package org.letscareer.letscareer.domain.curation.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CurationErrorCode implements ErrorCode {
    CURATION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 큐레이션입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
