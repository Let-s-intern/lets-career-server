package org.letscareer.letscareer.domain.attendance.mapper;

import org.letscareer.letscareer.domain.attendance.dto.response.AttendanceAdminListResponseDto;
import org.letscareer.letscareer.domain.attendance.vo.*;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeFeedbackMissionAttendanceResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeFeedbackMissionAttendancesResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeMissionAttendancesResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceMapper {
    public AttendanceAdminListResponseDto toAttendanceAdminListResponseDto(List<AttendanceAdminVo> attendanceAdminList) {
        return AttendanceAdminListResponseDto.of(attendanceAdminList);
    }

    public GetChallengeMissionAttendancesResponseDto toGetChallengeMissionAttendancesResponseDto(List<MissionAttendanceWithOptionsVo> attendanceVos) {
        return GetChallengeMissionAttendancesResponseDto.of(attendanceVos);
    }

    public GetChallengeFeedbackMissionAttendancesResponseDto toGetChallengeFeedbackMissionAttendancesResponseDto(List<FeedbackMissionAttendanceVo> attendanceVos) {
        return GetChallengeFeedbackMissionAttendancesResponseDto.of(attendanceVos);
    }

    public GetChallengeFeedbackMissionAttendanceResponseDto toGetChallengeFeedbackMissionAttendanceResponseDto(FeedbackMissionAttendanceDetailVo attendanceVo) {
        return GetChallengeFeedbackMissionAttendanceResponseDto.of(attendanceVo);
    }
}
