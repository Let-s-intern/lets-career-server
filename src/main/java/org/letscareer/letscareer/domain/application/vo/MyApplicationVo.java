package org.letscareer.letscareer.domain.application.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;

import java.time.LocalDateTime;

public record MyApplicationVo(
        Long id,
        ApplicationStatus status,
        String programTitle,
        LocalDateTime programStartDate,
        LocalDateTime programEndDate
) {
    @Builder
    public MyApplicationVo(Long id,
                           Boolean paymentIsConfirmed,
                           String programTitle,
                           LocalDateTime programStartDate,
                           LocalDateTime programEndDate) {
        this(id, ApplicationStatus.of(paymentIsConfirmed, programEndDate), programTitle, programStartDate, programEndDate);
    }
}
