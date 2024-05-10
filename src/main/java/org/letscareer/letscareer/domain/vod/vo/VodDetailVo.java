package org.letscareer.letscareer.domain.vod.vo;

import lombok.Builder;

@Builder
public record VodDetailVo(
        String title,
        String shortDesc,
        String thumbnail,
        String job,
        Boolean isVisible
) {
}
