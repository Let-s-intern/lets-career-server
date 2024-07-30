package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramType;

@Builder(access = AccessLevel.PRIVATE)
public record ReviewParameter(
        String userName,
        String program,
        Long programId
) {
    public static ReviewParameter of(String userName, ProgramType programType, Long programId) {
        return ReviewParameter.builder()
                .userName(userName)
                .program(programType.getDesc())
                .programId(programId)
                .build();
    }
}
