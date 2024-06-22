package org.letscareer.letscareer.domain.vod.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.vod.vo.VodProfileVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetVodsResponseDto(
        List<VodProfileVo> programList,
        PageInfo pageInfo
) {
    public static GetVodsResponseDto of(Page<VodProfileVo> programList) {
        PageInfo pageInfo = PageInfo.of(programList);
        return GetVodsResponseDto.builder()
                .programList(programList.getContent())
                .pageInfo(pageInfo)
                .build();
    }
}
