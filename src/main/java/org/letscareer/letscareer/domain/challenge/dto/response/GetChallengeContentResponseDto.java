package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeContentVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeContentResponseDto(
        ChallengeContentVo content
) {
    public static GetChallengeContentResponseDto of(ChallengeContentVo content) {
        return GetChallengeContentResponseDto.builder()
                .content(content)
                .build();
    }
}
