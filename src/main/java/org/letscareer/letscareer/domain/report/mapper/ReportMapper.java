package org.letscareer.letscareer.domain.report.mapper;

import org.letscareer.letscareer.domain.report.dto.res.GetReportsForAdminResponseDto;
import org.letscareer.letscareer.domain.report.vo.ReportForAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {
    public GetReportsForAdminResponseDto toGetReportsForAdminResponseDto(Page<ReportForAdminVo> reportForAdminInfos) {
        PageInfo pageInfo = PageInfo.of(reportForAdminInfos);
        return GetReportsForAdminResponseDto.of(reportForAdminInfos.getContent(), pageInfo);
    }
}
