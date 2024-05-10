package org.letscareer.letscareer.domain.mission.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.mission.vo.MissionAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MissionAdminListResponseDto(
        List<MissionAdminVo> missionAdminList
) {
    public static MissionAdminListResponseDto of(List<MissionAdminVo> missionAdminList) {
        return MissionAdminListResponseDto.builder()
                .missionAdminList(missionAdminList)
                .build();
    }
}
