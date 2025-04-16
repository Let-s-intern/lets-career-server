package org.letscareer.letscareer.domain.challengeoption.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challengeoption.vo.ChallengeOptionAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetAllChallengeOptionListResponseDto(
        List<ChallengeOptionAdminVo> challengeOptionList
) {
    public static GetAllChallengeOptionListResponseDto of(List<ChallengeOptionAdminVo> challengeOptionList) {
        return GetAllChallengeOptionListResponseDto.builder()
                .challengeOptionList(challengeOptionList)
                .build();
    }
}
