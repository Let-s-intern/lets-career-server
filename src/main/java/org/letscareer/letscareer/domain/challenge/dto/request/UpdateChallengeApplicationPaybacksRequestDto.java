package org.letscareer.letscareer.domain.challenge.dto.request;

import java.util.List;

public record UpdateChallengeApplicationPaybacksRequestDto(
        Integer price,
        String reason,
        List<Long> applicationIdList
) {
}
