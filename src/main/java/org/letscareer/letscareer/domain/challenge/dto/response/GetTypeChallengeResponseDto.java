package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeSimpleProfileVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetTypeChallengeResponseDto(
        List<ChallengeSimpleProfileVo> challengeList
) {
    public static GetTypeChallengeResponseDto of(List<ChallengeSimpleProfileVo> challengeList) {
        return GetTypeChallengeResponseDto.builder()
                .challengeList(challengeList)
                .build();
    }
}
