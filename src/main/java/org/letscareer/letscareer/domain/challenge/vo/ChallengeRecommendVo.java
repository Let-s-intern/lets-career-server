package org.letscareer.letscareer.domain.challenge.vo;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeRecommendVo(
        Long id,
        ProgramType programType,
        ProgramStatusType programStatusType,
        ChallengeType challengeType,
        String title,
        String thumbnail,
        String shortDesc,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime beginning,
        LocalDateTime deadline
) {
    public ChallengeRecommendVo(Long id,
                                ProgramType programType,
                                ChallengeType challengeType,
                                String title,
                                String thumbnail,
                                String shortDesc,
                                LocalDateTime startDate,
                                LocalDateTime endDate,
                                LocalDateTime beginning,
                                LocalDateTime deadline) {
        this(id, programType, ProgramStatusType.of(programType, beginning, deadline), challengeType, title, thumbnail, shortDesc, startDate, endDate, beginning, deadline);
    }
}
