package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewParameter(
        String userName,
        String program,
        Long programId
) {
    public static ReviewParameter of(String userName, String programType, Long programId) {
        return ReviewParameter.builder()
                .userName(userName)
                .program(programType)
                .programId(programId)
                .build();
    }
}
