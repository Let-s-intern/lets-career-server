package org.letscareer.letscareer.domain.vod.mapper;

import org.letscareer.letscareer.domain.admincalssification.vo.VodAdminClassificationDetailVo;
import org.letscareer.letscareer.domain.classification.vo.VodClassificationDetailVo;
import org.letscareer.letscareer.domain.vod.dto.response.GetVodDetailResponseDto;
import org.letscareer.letscareer.domain.vod.dto.response.GetVodsResponseDto;
import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;
import org.letscareer.letscareer.domain.vod.vo.VodProfileVo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodMapper {
    public GetVodDetailResponseDto toVodDetailResponseDto(VodDetailVo vodInfo,
                                                          List<VodClassificationDetailVo> programTypeInfo,
                                                          List<VodAdminClassificationDetailVo> adminProgramTypeInfo) {
        return GetVodDetailResponseDto.of(vodInfo, programTypeInfo, adminProgramTypeInfo);
    }

    public GetVodsResponseDto toGetVodsResponseDto(Page<VodProfileVo> vodProfileVos) {
        return GetVodsResponseDto.of(vodProfileVos);
    }
}
