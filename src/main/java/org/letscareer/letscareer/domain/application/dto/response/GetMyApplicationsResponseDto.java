package org.letscareer.letscareer.domain.application.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetMyApplicationsResponseDto(
        List<MyApplicationVo> applicationList
) {
    public static GetMyApplicationsResponseDto of(List<MyApplicationVo> applicationList) {
        return GetMyApplicationsResponseDto.builder()
                .applicationList(applicationList)
                .build();
    }
}
