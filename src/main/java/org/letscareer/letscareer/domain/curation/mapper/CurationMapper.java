package org.letscareer.letscareer.domain.curation.mapper;

import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationResponseDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationsResponseDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetCurationResponseDto;
import org.letscareer.letscareer.domain.curation.vo.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurationMapper {
    public GetAdminCurationsResponseDto toGetAdminCurationsResponseDto(List<AdminCurationVo> adminCurationVoList) {
        return GetAdminCurationsResponseDto.of(adminCurationVoList);
    }

    public GetAdminCurationResponseDto toGetAdminCurationResponseDto(AdminCurationDetailVo adminCurationDetailVo,
                                                                     List<AdminCurationItemVo> adminCurationItemVos) {
        return GetAdminCurationResponseDto.of(adminCurationDetailVo, adminCurationItemVos);
    }

    public GetCurationResponseDto toGetCurationResponseDto(CurationVo curationVo, List<CurationItemVo> curationItemVos) {
        return GetCurationResponseDto.of(curationVo, curationItemVos);
    }
}
