package org.letscareer.letscareer.domain.application.mapper;

import org.letscareer.letscareer.domain.application.dto.response.GetLiveApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
import org.letscareer.letscareer.domain.live.dto.response.GetLiveExisingApplicationResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LiveApplicationMapper {

    public GetLiveApplicationsResponseDto toGetLiveApplicationsResponseDto(List<AdminLiveApplicationVo> applicationList) {
        return GetLiveApplicationsResponseDto.of(applicationList);
    }

    public GetLiveExisingApplicationResponseDto toGetLiveExisingApplicationResponseDto(Boolean applied) {
        return GetLiveExisingApplicationResponseDto.of(applied);
    }
}
