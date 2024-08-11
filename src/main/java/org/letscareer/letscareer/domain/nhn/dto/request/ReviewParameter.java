package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewParameter(
        String userName,
        String program,
        Long programId,
        Long applicationId
) {
    public static ReviewParameter of(String userName, String programType, Long programId, Long applicationId) {
        return ReviewParameter.builder()
                .userName(userName)
                .program(programType)
                .programId(programId)
                .applicationId(applicationId)
                .build();
    }
}
