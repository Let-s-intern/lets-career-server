package org.letscareer.letscareer.domain.mission.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;

import java.time.LocalDateTime;
import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MissionAdminResponseDto(
        Long id,
        String title,
        Integer th,
        String missionType,
        MissionStatusType missionStatusType,
        Integer attendanceCount,
        Integer lateAttendanceCount,
        Long applicationCount,
        Integer score,
        Integer lateScore,
        LocalDateTime startDate,
        LocalDateTime endDate,
        List<ContentsMissionVo> essentialContentsList,
        List<ContentsMissionVo> additionalContentsList
) {
    public static MissionAdminResponseDto of(MissionForChallengeVo vo,
                                             Long applicationCount,
                                             List<ContentsMissionVo> essentialContentsList,
                                             List<ContentsMissionVo> additionalContentsList) {
        return MissionAdminResponseDto.builder()
                .id(vo.id())
                .title(vo.title())
                .th(vo.th())
                .missionType(vo.missionTag())
                .missionStatusType(vo.missionStatusType())
                .attendanceCount(vo.attendanceCount())
                .lateAttendanceCount(vo.lateAttendanceCount())
                .applicationCount(applicationCount)
                .score(vo.score())
                .lateScore(vo.lateScore())
                .startDate(vo.startDate())
                .endDate(vo.endDate())
                .essentialContentsList(essentialContentsList)
                .additionalContentsList(additionalContentsList)
                .build();
    }
}
