package org.letscareer.letscareer.domain.curation.mapper;

import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationResponseDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationsResponseDto;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationDetailVo;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationVo;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationItemVo;
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
}
