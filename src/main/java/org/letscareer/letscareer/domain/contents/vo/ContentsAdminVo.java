package org.letscareer.letscareer.domain.contents.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.contents.type.ContentsType;

import java.time.LocalDateTime;

@Builder
public record ContentsAdminVo(
        Long id,
        ContentsType type,
        String title,
        String link,
        LocalDateTime createDate
) {
}
