package org.letscareer.letscareer.domain.application.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeApplicationsResponseDto(
        List<AdminChallengeApplicationVo> applicationList
) {
    public static GetChallengeApplicationsResponseDto of(List<AdminChallengeApplicationVo> applicationList) {
        return GetChallengeApplicationsResponseDto.builder()
                .applicationList(applicationList)
                .build();
    }
}
