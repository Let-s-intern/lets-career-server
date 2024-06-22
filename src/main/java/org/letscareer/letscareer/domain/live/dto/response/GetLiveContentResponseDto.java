package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.live.vo.LiveContentVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveContentResponseDto(
        LiveContentVo contentVo
) {
    public static GetLiveContentResponseDto of(LiveContentVo contentVo) {
        return GetLiveContentResponseDto.builder()
                .contentVo(contentVo)
                .build();
    }
}
