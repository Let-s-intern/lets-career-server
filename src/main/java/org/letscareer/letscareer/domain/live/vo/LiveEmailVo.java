package org.letscareer.letscareer.domain.live.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.live.type.ProgressType;

import java.time.LocalDateTime;

@Builder
public record LiveEmailVo(
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate,
        ProgressType progressType,
        String place,
        String zoomLink,
        String zoomPassword
) {
}
