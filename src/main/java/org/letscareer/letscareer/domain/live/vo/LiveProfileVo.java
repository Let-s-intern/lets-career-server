package org.letscareer.letscareer.domain.live.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LiveProfileVo(
        Long id,
        String title,
        String shortDesc,
        String thumbnail,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline
) {
}
