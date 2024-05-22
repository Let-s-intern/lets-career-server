package org.letscareer.letscareer.domain.contents.vo;

import lombok.Builder;

@Builder
public record ContentsAdminSimpleVo(
        Long id,
        String title
) {
}
