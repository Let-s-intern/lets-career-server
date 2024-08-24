package org.letscareer.letscareer.domain.report.dto.res;

import org.letscareer.letscareer.domain.report.vo.MyReportVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

public record GetMyReportResponseDto(
        List<MyReportVo> myReportInfos,
        PageInfo pageInfo
) {
}
