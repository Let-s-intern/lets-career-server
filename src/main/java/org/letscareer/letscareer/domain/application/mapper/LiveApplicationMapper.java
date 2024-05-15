package org.letscareer.letscareer.domain.application.mapper;

import org.letscareer.letscareer.domain.application.dto.response.GetLiveApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LiveApplicationMapper {

    public GetLiveApplicationsResponseDto toGetLiveApplicationsResponseDto(List<AdminLiveApplicationVo> applicationList) {
        return GetLiveApplicationsResponseDto.of(applicationList);
    }
}
