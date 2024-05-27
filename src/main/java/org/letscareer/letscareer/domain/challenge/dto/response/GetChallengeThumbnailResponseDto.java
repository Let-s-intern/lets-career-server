package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeThumbnailVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeThumbnailResponseDto(
        ChallengeThumbnailVo thumbnail
) {
    public static GetChallengeThumbnailResponseDto of(ChallengeThumbnailVo thumbnail) {
        return GetChallengeThumbnailResponseDto.builder()
                .thumbnail(thumbnail)
                .build();
    }
}
