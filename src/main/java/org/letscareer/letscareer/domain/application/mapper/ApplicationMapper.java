package org.letscareer.letscareer.domain.application.mapper;

import org.letscareer.letscareer.domain.application.dto.response.CreateApplicationResponseDto;
import org.letscareer.letscareer.domain.application.dto.response.GetMyApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationMapper {
    public GetMyApplicationsResponseDto toGetMyApplicationsResponseDto(List<MyApplicationVo> applicationList) {
        return GetMyApplicationsResponseDto.of(applicationList);
    }

    public CreateApplicationResponseDto toCreateApplicationResponseDto(TossPaymentsResponseDto responseDto) {
        return CreateApplicationResponseDto.of(responseDto);
    }
}
