package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.vo.UserChallengeApplicationVo;
import org.letscareer.letscareer.domain.mission.dto.response.MissionApplicationScoreResponseDto;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeApplicationsPaybackResponseDto(
        List<MissionApplicationScoreResponseDto> missionApplications,
        PageInfo pageInfo
) {
    public static GetChallengeApplicationsPaybackResponseDto of(List<MissionApplicationScoreResponseDto> missionApplications,
                                                                Page<UserChallengeApplicationVo> challengeApplicationVos) {
        PageInfo pageInfo = PageInfo.of(challengeApplicationVos);
        return GetChallengeApplicationsPaybackResponseDto.builder()
                .missionApplications(missionApplications)
                .pageInfo(pageInfo)
                .build();
    }
}
