package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeEmailContentsResponseDto(
        String title,
        String contents
) {
    public static GetChallengeEmailContentsResponseDto of(String title, String contents) {
        return GetChallengeEmailContentsResponseDto.builder()
                .title(title)
                .contents(contents)
                .build();
    }
}
