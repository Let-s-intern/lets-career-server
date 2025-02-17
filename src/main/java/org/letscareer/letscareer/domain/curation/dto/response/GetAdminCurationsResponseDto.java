package org.letscareer.letscareer.domain.curation.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.curation.vo.CurationAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetAdminCurationsResponseDto(
        List<CurationAdminVo> curationList
) {
    public static GetAdminCurationsResponseDto of(List<CurationAdminVo> curationList) {
        return GetAdminCurationsResponseDto.builder()
                .curationList(curationList)
                .build();
    }
}
