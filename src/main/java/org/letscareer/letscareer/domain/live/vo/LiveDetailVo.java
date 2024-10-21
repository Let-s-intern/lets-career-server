package org.letscareer.letscareer.domain.live.vo;

import org.letscareer.letscareer.domain.live.type.ProgressType;

import java.time.LocalDateTime;

public record LiveDetailVo(
        Long id,
        String title,
        String shortDesc,
        String desc,
        String criticalNotice,
        Integer participationCount,
        String thumbnail,
        String mentorName,
        String mentorImg,
        String mentorCompany,
        String mentorJob,
        String mentorCareer,
        String mentorIntroduction,
        String job,
        String place,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime beginning,
        LocalDateTime deadline,
        ProgressType progressType
) {
}
