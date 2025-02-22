package org.letscareer.letscareer.domain.curation.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetCurationsResponseDto(
        List<AdminCurationVo> curationList
) {
    public static GetCurationsResponseDto of(List<AdminCurationVo> curationList) {
        return GetCurationsResponseDto.builder()
                .curationList(curationList)
                .build();
    }
}
