package org.letscareer.letscareer.domain.application.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationWithOptionsVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeApplicationsResponseDto(
        List<AdminChallengeApplicationWithOptionsVo> applicationList
) {
    public static GetChallengeApplicationsResponseDto of(List<AdminChallengeApplicationWithOptionsVo> applicationList) {
        return GetChallengeApplicationsResponseDto.builder()
                .applicationList(applicationList)
                .build();
    }
}
