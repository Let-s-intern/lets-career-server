package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeMyMissionsResponseDto(
    List<?> missionList
) {
    public static <T> GetChallengeMyMissionsResponseDto of(List<T> missionList) {
        return GetChallengeMyMissionsResponseDto.builder()
                .missionList(missionList)
                .build();
    }
}
