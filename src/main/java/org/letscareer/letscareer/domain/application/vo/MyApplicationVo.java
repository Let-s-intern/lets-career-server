package org.letscareer.letscareer.domain.application.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

@Builder
public record MyApplicationVo(
        Long id,
        LocalDateTime createDate,
        ApplicationStatus status,
        Long programId,
        ProgramType programType,
        ProgramStatusType programStatusType,
        ReportType reportType,
        String programTitle,
        String programShortDesc,
        String programThumbnail,
        LocalDateTime programStartDate,
        LocalDateTime programEndDate,
        Long reviewId,
        Long paymentId
) {
    public MyApplicationVo(Long id,
                           LocalDateTime createDate,
                           Boolean isCanceled,
                           Long programId,
                           ProgramType programType,
                           ReportType reportType,
                           String programTitle,
                           String programShortDesc,
                           String programThumbnail,
                           LocalDateTime programStartDate,
                           LocalDateTime programEndDate,
                           Long reviewId,
                           Long paymentId) {
        this(
                id,
                createDate,
                ApplicationStatus.of(isCanceled, programEndDate),
                programId,
                programType,
                ProgramStatusType.of(programType, programStartDate, programEndDate),
                reportType,
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
