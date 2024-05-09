package org.letscareer.letscareer.domain.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.service.ChallengeService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge")
@RestController
public class ChallengeV1Controller {
    private final ChallengeService challengeService;

    @Operation(summary = "챌린지 생성")
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createChallengeProgram(@RequestBody final CreateChallengeRequestDto requestDto) {
        challengeService.createChallenge(requestDto);
        return SuccessResponse.created(null);
    }
}
