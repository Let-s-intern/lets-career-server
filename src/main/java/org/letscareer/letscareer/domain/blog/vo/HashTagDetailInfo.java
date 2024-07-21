package org.letscareer.letscareer.domain.blog.vo;

import java.time.LocalDateTime;

public record HashTagDetailInfo(
        Long id,
        String title,
        LocalDateTime createDate,
        LocalDateTime lastModifiedDate
) {
}
