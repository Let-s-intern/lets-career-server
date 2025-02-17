package org.letscareer.letscareer.domain.curation.mapper;

import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationResponseDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationsResponseDto;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationDetailVo;
import org.letscareer.letscareer.domain.curation.vo.CurationAdminVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurationMapper {
    public GetAdminCurationsResponseDto toGetAdminCurationsResponseDto(List<CurationAdminVo> curationAdminVoList) {
        return GetAdminCurationsResponseDto.of(curationAdminVoList);
    }

    public GetAdminCurationResponseDto toGetAdminCurationResponseDto(AdminCurationDetailVo adminCurationDetailVo) {
        return GetAdminCurationResponseDto.of(adminCurationDetailVo);
    }
}
