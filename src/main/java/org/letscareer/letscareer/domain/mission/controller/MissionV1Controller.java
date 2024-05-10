package org.letscareer.letscareer.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.request.UpdateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionAdminListResponseDto;
import org.letscareer.letscareer.domain.mission.service.MissionService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RequiredArgsConstructor
@RequestMapping("/api/v1/mission")
@RestController
public class MissionV1Controller {
    private final MissionService missionService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createMission(@RequestBody @Valid CreateMissionRequestDto createMissionRequestDto) {
        missionService.createMission(createMissionRequestDto);
        return SuccessResponse.created(null);
    }

    @GetMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> getMissionsForAdmin() {
        MissionAdminListResponseDto responseDto = missionService.getMissionsForAdmin();
        return SuccessResponse.ok(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateMission(@PathVariable(name = "id") String missionId,
                                                            @RequestBody UpdateMissionRequestDto updateMissionRequestDto) {
        missionService.updateMission(missionId, updateMissionRequestDto);
        return SuccessResponse.ok(null);
    }
}
