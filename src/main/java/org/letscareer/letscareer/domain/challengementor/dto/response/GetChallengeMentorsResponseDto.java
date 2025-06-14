package org.letscareer.letscareer.domain.challengementor.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challengementor.vo.ChallengeMentorAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeMentorsResponseDto(
        List<ChallengeMentorAdminVo> mentorList
) {
    public static GetChallengeMentorsResponseDto of(List<ChallengeMentorAdminVo> mentorList) {
        return GetChallengeMentorsResponseDto.builder()
                .mentorList(mentorList)
                .build();
    }
}
