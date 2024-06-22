package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.live.vo.LiveThumbnailVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveThumbnailResponseDto(
        LiveThumbnailVo thumbnail
) {
    public static GetLiveThumbnailResponseDto of(LiveThumbnailVo thumbnail) {
        return GetLiveThumbnailResponseDto.builder()
                .thumbnail(thumbnail)
                .build();
    }
}
