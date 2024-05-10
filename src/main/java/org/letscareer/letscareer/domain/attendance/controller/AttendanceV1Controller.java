package org.letscareer.letscareer.domain.attendance.controller;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.dto.response.AttendanceAdminListResponseDto;
import org.letscareer.letscareer.domain.attendance.service.AttendanceService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/attendance")
@RestController
public class AttendanceV1Controller {
    private final AttendanceService attendanceService;

    @GetMapping("/admin/{id}")
    public ResponseEntity<SuccessResponse<?>> getAttendancesOfChallenge(@PathVariable(name = "id") Long challengeId) {
        AttendanceAdminListResponseDto responseDto = attendanceService.getAttendancesOfChallenge(challengeId);
        return SuccessResponse.ok(responseDto);
    }
}
