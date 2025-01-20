package org.letscareer.letscareer.domain.review.vo;

import org.letscareer.letscareer.domain.review.type.ReviewProgramType;

import java.time.LocalDateTime;

public record ReviewInfoVo(
        Long reviewId,
        ReviewProgramType type,
        LocalDateTime createDate,
        String goodPoint,
        String badPoint,
        String programTitle,
        String programThumbnail,
        String missionTitle,
        Integer missionTh,
        String name,
        String wishJob,
        String wishCompany
) {
}
