package org.letscareer.letscareer.domain.program.vo;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ProgramForConditionVo(
        Long id,
        ProgramType programType,
        ProgramStatusType programStatusType,
        String title,
        String thumbnail,
        String shortDesc,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime beginning,
        LocalDateTime deadline
) {
    public ProgramForConditionVo(Long id,
                                 ProgramType programType,
                                 String title,
                                 String thumbnail,
                                 String shortDesc,
                                 LocalDateTime startDate,
                                 LocalDateTime endDate,
                                 LocalDateTime beginning,
                                 LocalDateTime deadline) {
        this(id, programType, ProgramStatusType.of(programType, beginning, deadline), title, thumbnail, shortDesc, startDate, endDate, beginning, deadline);
    }
}
