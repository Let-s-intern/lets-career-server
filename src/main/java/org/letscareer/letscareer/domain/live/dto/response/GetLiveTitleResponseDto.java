package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.live.vo.LiveTitleVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveTitleResponseDto(
        String title
) {
    public static GetLiveTitleResponseDto of(LiveTitleVo liveTitleVo) {
        return GetLiveTitleResponseDto.builder()
                .title(liveTitleVo.title())
                .build();
    }
}
