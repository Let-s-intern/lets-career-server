package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.vo.MyReportVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetMyReportResponseDto(
        List<MyReportVo> myReportInfos,
        PageInfo pageInfo
) {
    public static GetMyReportResponseDto of(List<MyReportVo> myReportInfos,
                                            PageInfo pageInfo) {
        return GetMyReportResponseDto.builder()
                .myReportInfos(myReportInfos)
                .pageInfo(pageInfo)
                .build();
    }
}
