package org.letscareer.letscareer.domain.contents.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.contents.type.ContentsType;

@Builder
public record ContentsDetailVo(
        String title,
        String link,
        ContentsType contentsType
) {
}
