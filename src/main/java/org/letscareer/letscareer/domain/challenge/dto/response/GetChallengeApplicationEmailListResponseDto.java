package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeApplicationEmailListResponseDto(
        List<String> emailList
) {
    public static GetChallengeApplicationEmailListResponseDto of(List<String> emailList) {
        return GetChallengeApplicationEmailListResponseDto.builder()
                .emailList(emailList)
                .build();
    }
}
