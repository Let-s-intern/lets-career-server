package org.letscareer.letscareer.domain.vod.mapper;

import org.letscareer.letscareer.domain.classification.vo.VodClassificationDetailVo;
import org.letscareer.letscareer.domain.vod.dto.response.GetVodDetailResponseDto;
import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodMapper {
    public GetVodDetailResponseDto createVodDetailResponseDto(VodDetailVo vodInfo,
                                                              List<VodClassificationDetailVo> programTypeInfo) {
        return GetVodDetailResponseDto.of(vodInfo, programTypeInfo);
    }
}
