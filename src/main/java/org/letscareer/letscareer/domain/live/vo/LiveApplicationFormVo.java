package org.letscareer.letscareer.domain.live.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LiveApplicationFormVo(
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline
) {
}
