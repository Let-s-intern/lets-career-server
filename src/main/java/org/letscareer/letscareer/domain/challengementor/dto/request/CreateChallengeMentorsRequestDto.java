package org.letscareer.letscareer.domain.challengementor.dto.request;

import java.util.List;

public record CreateChallengeMentorsRequestDto(
        List<Long> mentorIdList
) {
}
