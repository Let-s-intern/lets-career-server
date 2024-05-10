package org.letscareer.letscareer.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.service.MissionService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
