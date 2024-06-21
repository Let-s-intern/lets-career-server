package org.letscareer.letscareer.domain.mission.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;
import org.letscareer.letscareer.domain.mission.vo.MissionDetailVo;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record GetMissionDetailResponseDto(
        Long id,
        String title,
        MissionStatusType missionStatusType,
        Integer attendanceCount,
        Integer lateAttendanceCount,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
    public static GetMissionDetailResponseDto of(MissionDetailVo vo) {
        return GetMissionDetailResponseDto.builder()
                .id(vo.id())
                .title(vo.title())
                .missionStatusType(vo.missionStatusType())
                .attendanceCount(vo.attendanceCount())
                .lateAttendanceCount(vo.lateAttendanceCount())
                .startDate(vo.startDate())
                .endDate(vo.endDate())
                .build();
    }
}
