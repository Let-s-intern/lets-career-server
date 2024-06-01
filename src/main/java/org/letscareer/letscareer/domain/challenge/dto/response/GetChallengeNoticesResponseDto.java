package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeNoticesResponseDto(
        List<ChallengeNoticeVo> challengeNoticeList
) {
    public static GetChallengeNoticesResponseDto of(List<ChallengeNoticeVo> challengeNoticeList) {
        return GetChallengeNoticesResponseDto.builder()
                .challengeNoticeList(challengeNoticeList)
                .build();
    }
}
