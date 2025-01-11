package org.letscareer.letscareer.domain.challenge.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChallengeSimpleProfileVo(
        Long id,
        String title,
        LocalDateTime beginning,
        LocalDateTime deadline
) {
}
