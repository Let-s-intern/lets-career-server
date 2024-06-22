package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challengeguide.vo.ChallengeGuideVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeGuidesResponseDto(
        List<ChallengeGuideVo> challengeGuideList
) {
    public static GetChallengeGuidesResponseDto of(List<ChallengeGuideVo> challengeGuideList) {
        return GetChallengeGuidesResponseDto.builder()
                .challengeGuideList(challengeGuideList)
                .build();
    }
}
