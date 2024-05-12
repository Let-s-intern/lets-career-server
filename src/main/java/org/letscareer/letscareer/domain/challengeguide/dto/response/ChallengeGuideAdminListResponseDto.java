package org.letscareer.letscareer.domain.challengeguide.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challengeguide.vo.ChallengeGuideAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeGuideAdminListResponseDto(
        List<ChallengeGuideAdminVo> challengeGuideAdminList
) {
    public static ChallengeGuideAdminListResponseDto of(List<ChallengeGuideAdminVo> challengeGuideAdminList) {
        return ChallengeGuideAdminListResponseDto.builder()
                .challengeGuideAdminList(challengeGuideAdminList)
                .build();
    }
}
