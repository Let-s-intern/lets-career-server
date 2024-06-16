package org.letscareer.letscareer.domain.mission.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MissionAdminListResponseDto(
        List<MissionAdminResponseDto> missionList
) {
    public static MissionAdminListResponseDto of(List<MissionAdminResponseDto> missionList) {
        return MissionAdminListResponseDto.builder()
                .missionList(missionList)
                .build();
    }
}
