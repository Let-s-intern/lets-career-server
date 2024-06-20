package org.letscareer.letscareer.domain.challenge.vo;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeApplicationFormVo(
        ProgramStatusType statusType,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime beginning,
        LocalDateTime deadline
) {
    public ChallengeApplicationFormVo(LocalDateTime startDate,
                                      LocalDateTime endDate,
                                      LocalDateTime beginning,
                                      LocalDateTime deadline) {
        this(ProgramStatusType.of(ProgramType.CHALLENGE, beginning, deadline), startDate, endDate, beginning, deadline);
    }
}
