package org.letscareer.letscareer.domain.mission.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.mission.vo.FeedbackMissionAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record FeedbackMissionAdminListResponseDto(
        List<FeedbackMissionAdminVo> missionList
) {
    public static FeedbackMissionAdminListResponseDto of(List<FeedbackMissionAdminVo> missionList) {
        return FeedbackMissionAdminListResponseDto.builder()
                .missionList(missionList)
                .build();
    }
}
