package org.letscareer.letscareer.domain.challengeguide.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeguide.dto.request.CreateChallengeGuideRequestDto;
import org.letscareer.letscareer.domain.challengeguide.dto.request.UpdateChallengeGuideRequestDto;
import org.letscareer.letscareer.domain.challengeguide.service.ChallengeGuideService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge-guide")
@RestController
public class ChallengeGuideV1Controller {
    private final ChallengeGuideService challengeGuideService;

    @Operation(summary = "챌린지 가이드 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> createChallengeGuide(@PathVariable(name = "id") final Long challengeId,
                                                                   @RequestBody final CreateChallengeGuideRequestDto createChallengeGuideRequestDto) {
        challengeGuideService.createChallengeGuide(challengeId, createChallengeGuideRequestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "챌린지 가이드 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateChallengeGuide(@PathVariable(name = "id") final Long challengeGuideId,
                                                                   @RequestBody final UpdateChallengeGuideRequestDto updateChallengeGuideRequestDto) {
        challengeGuideService.updateChallengeGuide(challengeGuideId, updateChallengeGuideRequestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "챌린지 가이드 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> deleteChallengeGuide(@PathVariable(name = "id") final Long challengeGuideId) {
        challengeGuideService.deleteChallengeGuide(challengeGuideId);
        return SuccessResponse.ok(null);
    }
}
