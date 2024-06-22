package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.live.vo.LiveProfileVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetLivesResponseDto(
        List<LiveProfileVo> programList,
        PageInfo pageInfo
) {
    public static GetLivesResponseDto of(Page<LiveProfileVo> programList) {
        PageInfo pageInfo = PageInfo.of(programList);
        return GetLivesResponseDto.builder()
                .programList(programList.getContent())
                .pageInfo(pageInfo)
                .build();
    }
}
