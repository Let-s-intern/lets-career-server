package org.letscareer.letscareer.domain.vod.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.classification.vo.VodClassificationDetailVo;
import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetVodDetailResponseDto(
        VodDetailVo vodInfo,
        List<VodClassificationDetailVo> programTypeInfo
) {
    public static GetVodDetailResponseDto of(VodDetailVo vodInfo,
                                             List<VodClassificationDetailVo> programTypeInfo) {
        return GetVodDetailResponseDto.builder()
                .vodInfo(vodInfo)
                .programTypeInfo(programTypeInfo)
                .build();
    }
}
