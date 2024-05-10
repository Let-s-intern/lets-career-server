package org.letscareer.letscareer.domain.attendance.controller;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.dto.request.AttendanceUpdateRequestDto;
import org.letscareer.letscareer.domain.attendance.dto.response.AttendanceAdminListResponseDto;
import org.letscareer.letscareer.domain.attendance.service.AttendanceService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/attendance")
@RestController
public class AttendanceV1Controller {
    private final AttendanceService attendanceService;

    @GetMapping("/admin/{id}")
    public ResponseEntity<SuccessResponse<?>> getAttendancesOfChallenge(@PathVariable(name = "id") final Long challengeId) {
        AttendanceAdminListResponseDto responseDto = attendanceService.getAttendancesOfChallenge(challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @PatchMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> updateAttendanceAdmin(@PathVariable(name = "id") final Long attendanceId,
                                                                    @RequestBody final AttendanceUpdateRequestDto attendanceUpdateRequestDto) {
        attendanceService.updateAttendanceAdmin(attendanceId, attendanceUpdateRequestDto);
        return SuccessResponse.ok(null);
    }
}
