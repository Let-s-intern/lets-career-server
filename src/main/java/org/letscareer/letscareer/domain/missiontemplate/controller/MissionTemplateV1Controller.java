package org.letscareer.letscareer.domain.missiontemplate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.CreateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.service.MissionTemplateService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/mission-template")
@RestController
public class MissionTemplateV1Controller {
    private final MissionTemplateService missionTemplateService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createMissionTemplate(@RequestBody @Valid CreateMissionTemplateRequestDto createMissionTemplateRequestDto) {
        missionTemplateService.createMissionTemplate(createMissionTemplateRequestDto);
        return SuccessResponse.created(null);
    }
}
