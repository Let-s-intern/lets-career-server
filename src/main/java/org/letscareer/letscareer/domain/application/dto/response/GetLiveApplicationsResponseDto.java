package org.letscareer.letscareer.domain.application.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveApplicationsResponseDto(
        List<AdminLiveApplicationVo> applicationList
) {
    public static GetLiveApplicationsResponseDto of(List<AdminLiveApplicationVo> applicationList) {
        return GetLiveApplicationsResponseDto.builder()
                .applicationList(applicationList)
                .build();
    }
}
