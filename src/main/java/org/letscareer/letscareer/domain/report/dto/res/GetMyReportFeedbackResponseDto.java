package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.vo.MyReportFeedbackVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetMyReportFeedbackResponseDto(
        List<MyReportFeedbackVo> myReportFeedbackInfos,
        PageInfo pageInfo
) {
    public static GetMyReportFeedbackResponseDto of(List<MyReportFeedbackVo> myReportFeedbackInfos,
                                                    PageInfo pageInfo) {
        return GetMyReportFeedbackResponseDto.builder()
                .myReportFeedbackInfos(myReportFeedbackInfos)
                .pageInfo(pageInfo)
                .build();
    }
}
