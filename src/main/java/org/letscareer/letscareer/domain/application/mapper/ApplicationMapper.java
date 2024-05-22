package org.letscareer.letscareer.domain.application.mapper;

import org.letscareer.letscareer.domain.application.dto.response.GetMyApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationMapper {
    public GetMyApplicationsResponseDto toGetMyApplicationsResponseDto(List<MyApplicationVo> applicationList) {
        return GetMyApplicationsResponseDto.of(applicationList);
    }
}
