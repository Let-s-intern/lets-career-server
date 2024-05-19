package org.letscareer.letscareer.domain.vod.vo;

import lombok.Builder;

@Builder
public record VodDetailVo(
        Long id,
        String title,
        String shortDesc,
        String thumbnail,
        String job,
        String link,
        Boolean isVisible
) {
}
