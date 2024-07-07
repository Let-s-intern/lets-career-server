package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveMentorPasswordResponseDto(
        String mentorPassword
) {
    public static GetLiveMentorPasswordResponseDto of(String mentorPassword) {
        return GetLiveMentorPasswordResponseDto.builder()
                .mentorPassword(mentorPassword)
                .build();
    }
}
