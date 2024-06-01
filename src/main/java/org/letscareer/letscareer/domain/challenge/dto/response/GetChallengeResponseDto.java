package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeProfileVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengesResponseDto(
        List<ChallengeProfileVo> programList,
        PageInfo pageInfo
) {
    public static GetChallengesResponseDto of(Page<ChallengeProfileVo> programList) {
        PageInfo pageInfo = PageInfo.of(programList);
        return GetChallengesResponseDto.builder()
                .programList(programList.getContent())
                .pageInfo(pageInfo)
                .build();
    }
}
