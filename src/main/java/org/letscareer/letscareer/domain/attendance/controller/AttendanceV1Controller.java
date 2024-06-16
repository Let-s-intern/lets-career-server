package org.letscareer.letscareer.domain.attendance.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.dto.request.AttendanceCreateRequestDto;
import org.letscareer.letscareer.domain.attendance.dto.request.AttendanceUpdateRequestDto;
import org.letscareer.letscareer.domain.attendance.dto.response.AttendanceAdminListResponseDto;
import org.letscareer.letscareer.domain.attendance.service.AttendanceService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/attendance")
@RestController
public class AttendanceV1Controller {
    private final AttendanceService attendanceService;

    @Operation(summary = "출석 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> createAttendance(@PathVariable(name = "id") final Long missionId,
                                                               @RequestBody AttendanceCreateRequestDto attendanceCreateRequestDto,
                                                               @CurrentUser User user) {
        attendanceService.createAttendance(missionId, attendanceCreateRequestDto, user.getId());
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[어드민] 챌린지 1개의 출석 전체 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AttendanceAdminListResponseDto.class)))
    })
    @GetMapping("/admin/{id}")
    public ResponseEntity<SuccessResponse<?>> getAttendancesOfChallenge(@PathVariable(name = "id") final Long challengeId) {
        AttendanceAdminListResponseDto responseDto = attendanceService.getAttendancesOfChallenge(challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 출석 업데이트", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> updateAttendanceAdmin(@PathVariable(name = "id") final Long attendanceId,
                                                                    @RequestBody final AttendanceUpdateRequestDto attendanceUpdateRequestDto) {
        attendanceService.updateAttendanceAdmin(attendanceId, attendanceUpdateRequestDto);
        return SuccessResponse.ok(null);
    }
}
