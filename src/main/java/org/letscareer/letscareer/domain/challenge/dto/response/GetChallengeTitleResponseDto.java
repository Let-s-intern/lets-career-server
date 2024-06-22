package org.letscareer.letscareer.domain.challenge.dto.response;


import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeTitleVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeTitleResponseDto(
        String title
) {
    public static GetChallengeTitleResponseDto of(ChallengeTitleVo titleVo) {
        return GetChallengeTitleResponseDto.builder()
                .title(titleVo.title())
                .build();
    }
}
