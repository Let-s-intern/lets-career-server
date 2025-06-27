package org.letscareer.letscareer.domain.challengementor.dto.response;

import lombok.Builder;
import org.letscareer.letscareer.domain.challengementor.vo.MyChallengeMentorVo;

import java.util.List;

@Builder
public record GetMyChallengeMentorsResponseDto(
        List<MyChallengeMentorVo> myChallengeMentorVoList
) {
    public static GetMyChallengeMentorsResponseDto of(List<MyChallengeMentorVo> myChallengeMentorVoList) {
        return GetMyChallengeMentorsResponseDto.builder()
                .myChallengeMentorVoList(myChallengeMentorVoList)
                .build();
    }
}
