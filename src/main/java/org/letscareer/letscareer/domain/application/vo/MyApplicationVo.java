package org.letscareer.letscareer.domain.application.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder
public record MyApplicationVo(
        Long id,
        ApplicationStatus status,
        ProgramType programType,
        String programTitle,
        LocalDateTime programStartDate,
        LocalDateTime programEndDate
) {
    public MyApplicationVo(Long id,
                           Boolean paymentIsConfirmed,
                           ProgramType programType,
                           String programTitle,
                           LocalDateTime programStartDate,
                           LocalDateTime programEndDate) {
        this(id, ApplicationStatus.of(paymentIsConfirmed, programEndDate), programType, programTitle, programStartDate, programEndDate);
    }
}
