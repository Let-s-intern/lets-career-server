package org.letscareer.letscareer.domain.vod.vo;

import lombok.Builder;

@Builder
public record VodProfileVo(
        Long id,
        String title,
        String shortDesc,
        String thumbnail,
        String link
) {
}
