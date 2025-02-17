package org.letscareer.letscareer.domain.curation.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetAdminCurationsResponseDto(
        List<AdminCurationVo> curationList
) {
    public static GetAdminCurationsResponseDto of(List<AdminCurationVo> curationList) {
        return GetAdminCurationsResponseDto.builder()
                .curationList(curationList)
                .build();
    }
}
