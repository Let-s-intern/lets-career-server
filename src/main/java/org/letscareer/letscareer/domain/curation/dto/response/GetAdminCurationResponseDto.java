package org.letscareer.letscareer.domain.curation.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationDetailVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetAdminCurationResponseDto(
        AdminCurationDetailVo curationInfo
) {
    public static GetAdminCurationResponseDto of(AdminCurationDetailVo adminCurationDetailVo) {
        return GetAdminCurationResponseDto.builder()
                .curationInfo(adminCurationDetailVo)
                .build();
    }
}
