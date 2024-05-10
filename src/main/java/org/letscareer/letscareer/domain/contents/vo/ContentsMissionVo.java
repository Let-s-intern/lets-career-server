package org.letscareer.letscareer.domain.contents.vo;

import lombok.Builder;

@Builder
public record ContentsMissionVo(
        Long id,
        String title,
        String link
) {
}
