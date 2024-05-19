package org.letscareer.letscareer.domain.live.vo;

import org.letscareer.letscareer.domain.live.type.ProgressType;

import java.time.LocalDateTime;

public record LiveDetailVo(
        Long id,
        String title,
        String shortDesc,
        String desc,
        Integer participationCount,
        String thumbnail,
        String mentorName,
        String job,
        String place,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline,
        ProgressType progressType
) {
}
