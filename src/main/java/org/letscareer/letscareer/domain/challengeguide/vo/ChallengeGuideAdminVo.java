package org.letscareer.letscareer.domain.challengeguide.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChallengeGuideAdminVo(
        Long id,
        String title,
        String link,
        LocalDateTime createDate
) {
}
