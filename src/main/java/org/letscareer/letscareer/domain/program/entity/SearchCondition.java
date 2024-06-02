package org.letscareer.letscareer.domain.program.entity;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record SearchCondition(
        List<ProgramType> type,
        List<ProgramClassification> typeList,
        List<ProgramStatusType> statusList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Pageable pageable
) {
    public static SearchCondition of(List<ProgramType> type,
                                     List<ProgramClassification> typeList,
                                     List<ProgramStatusType> statusList,
                                     LocalDateTime startDate,
                                     LocalDateTime endDate,
                                     Pageable pageable) {
        return SearchCondition.builder()
                .type(type)
                .typeList(typeList)
                .statusList(statusList)
                .startDate(startDate)
                .endDate(endDate)
                .pageable(pageable)
                .build();
    }
}
