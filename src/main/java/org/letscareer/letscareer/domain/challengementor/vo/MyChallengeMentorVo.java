package org.letscareer.letscareer.domain.challengementor.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder
public record MyChallengeMentorVo(
        Long challengeId,
        ProgramStatusType programStatusType,
        String title,
        String shortDesc,
        String thumbnail,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
    public MyChallengeMentorVo(Long challengeId,
                               String title,
                               String shortDesc,
                               String thumbnail,
                               LocalDateTime startDate,
                               LocalDateTime endDate) {
        this(
                challengeId,
                ProgramStatusType.of(ProgramType.CHALLENGE, startDate, endDate),
                title,
                shortDesc,
                thumbnail,
                startDate,
                endDate
        );
    }
}
