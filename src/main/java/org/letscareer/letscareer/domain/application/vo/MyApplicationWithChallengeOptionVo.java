package org.letscareer.letscareer.domain.application.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.price.type.ChallengePricePlanType;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MyApplicationWithChallengeOptionVo(
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
        Long paymentId,
        ChallengePricePlanType pricePlanType,
        List<String> challengeOptionList
) {
    public static MyApplicationWithChallengeOptionVo of(MyApplicationVo myApplicationVo, List<String> challengeOptionList) {
        return MyApplicationWithChallengeOptionVo.builder()
                .id(myApplicationVo.id())
                .createDate(myApplicationVo.createDate())
                .status(myApplicationVo.status())
                .programId(myApplicationVo.programId())
                .programType(myApplicationVo.programType())
                .programStatusType(myApplicationVo.programStatusType())
                .reportType(myApplicationVo.reportType())
                .programTitle(myApplicationVo.programTitle())
                .programShortDesc(myApplicationVo.programShortDesc())
                .programThumbnail(myApplicationVo.programThumbnail())
                .programStartDate(myApplicationVo.programStartDate())
                .programEndDate(myApplicationVo.programEndDate())
                .reviewId(myApplicationVo.reviewId())
                .paymentId(myApplicationVo.paymentId())
                .pricePlanType(myApplicationVo.pricePlanType())
                .challengeOptionList(challengeOptionList)
                .build();
    }
}
