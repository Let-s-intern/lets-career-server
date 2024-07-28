package org.letscareer.letscareer.domain.application.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder
public record MyApplicationVo(
        Long id,
        ApplicationStatus status,
        Long programId,
        ProgramType programType,
        ProgramStatusType programStatusType,
        String programTitle,
        String programShortDesc,
        String programThumbnail,
        LocalDateTime programStartDate,
        LocalDateTime programEndDate,
        Long reviewId,
        Long paymentId
) {
    public MyApplicationVo(Long id,
                           Boolean isCanceled,
                           Long programId,
                           ProgramType programType,
                           String programTitle,
                           String programShortDesc,
                           String programThumbnail,
                           LocalDateTime programStartDate,
                           LocalDateTime programEndDate,
                           Long reviewId,
                           Long paymentId) {
        this(
                id,
                ApplicationStatus.of(isCanceled, programEndDate),
                programId,
                programType,
                ProgramStatusType.of(programType, programStartDate, programEndDate),
                programTitle,
                programShortDesc,
                programThumbnail,
                programStartDate,
                programEndDate,
                reviewId,
                paymentId
        );
    }
}
