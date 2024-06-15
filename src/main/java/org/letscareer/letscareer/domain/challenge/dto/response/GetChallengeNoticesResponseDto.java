package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeNoticesResponseDto(
        List<ChallengeNoticeVo> challengeNoticeList,
        PageInfo pageInfo
) {
    public static GetChallengeNoticesResponseDto of(Page<ChallengeNoticeVo> challengeNoticeList) {
        return GetChallengeNoticesResponseDto.builder()
                .challengeNoticeList(challengeNoticeList.getContent())
                .pageInfo(PageInfo.of(challengeNoticeList))
                .build();
    }
}
