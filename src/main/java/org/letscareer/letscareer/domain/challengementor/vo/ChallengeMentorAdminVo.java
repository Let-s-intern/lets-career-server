package org.letscareer.letscareer.domain.challengementor.vo;

import lombok.Builder;

@Builder
public record ChallengeMentorAdminVo(
        Long challengeMentorId,
        Long userId,
        String name
) {
}
