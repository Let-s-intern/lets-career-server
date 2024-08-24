package org.letscareer.letscareer.domain.report.dto.res;

import org.letscareer.letscareer.domain.report.vo.MyReportFeedbackVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

public record GetMyReportFeedbackResponseDto(
        List<MyReportFeedbackVo> myReportFeedbackInfos,
        PageInfo pageInfo
) {
}
