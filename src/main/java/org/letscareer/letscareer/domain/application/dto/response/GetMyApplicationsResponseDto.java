package org.letscareer.letscareer.domain.application.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.vo.MyApplicationWithChallengeOptionVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetMyApplicationsResponseDto(
        List<MyApplicationWithChallengeOptionVo> applicationList
) {
    public static GetMyApplicationsResponseDto of(List<MyApplicationWithChallengeOptionVo> applicationList) {
        return GetMyApplicationsResponseDto.builder()
                .applicationList(applicationList)
                .build();
    }
}
