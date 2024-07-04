package org.letscareer.letscareer.domain.live.vo;

import java.time.LocalDateTime;

public record LiveMentorVo(
        Long id,
        String title,
        Long participationCount,
        String mentorName,
        String zoomLink,
        String zoomPassword,
        String place,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
