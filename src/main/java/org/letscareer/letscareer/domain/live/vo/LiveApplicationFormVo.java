package org.letscareer.letscareer.domain.live.vo;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record LiveApplicationFormVo(
        String criticalNotice,
        ProgramStatusType statusType,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime beginning,
        LocalDateTime deadline
) {
    public LiveApplicationFormVo(String criticalNotice,
                                 LocalDateTime startDate,
                                 LocalDateTime endDate,
                                 LocalDateTime beginning,
                                 LocalDateTime deadline) {
        this(criticalNotice, ProgramStatusType.of(ProgramType.LIVE, beginning, deadline), startDate, endDate, beginning, deadline);
    }
}
