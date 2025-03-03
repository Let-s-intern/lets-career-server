package org.letscareer.letscareer.domain.curation.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationDetailVo;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationItemVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetAdminCurationResponseDto(
        AdminCurationDetailVo curationInfo,
        List<AdminCurationItemVo> curationItemList
) {
    public static GetAdminCurationResponseDto of(AdminCurationDetailVo adminCurationDetailVo,
                                                 List<AdminCurationItemVo> curationItemList) {
        return GetAdminCurationResponseDto.builder()
                .curationInfo(adminCurationDetailVo)
                .curationItemList(curationItemList)
                .build();
    }
}
