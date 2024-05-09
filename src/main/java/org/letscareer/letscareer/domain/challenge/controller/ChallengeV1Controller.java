package org.letscareer.letscareer.domain.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeDetailResponseDto;
import org.letscareer.letscareer.domain.challenge.service.ChallengeService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge")
@RestController
public class ChallengeV1Controller {
    private final ChallengeService challengeService;

    @Operation(summary = "챌린지 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeDetailResponseDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> getLiveDetail(@PathVariable("id") final Long challengeId) {
        GetChallengeDetailResponseDto responseDto = challengeService.getChallengeDetail(challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "프로그램 신청자 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeApplicationsResponseDto.class)))
    })
    @GetMapping("/{id}/applications")
    public ResponseEntity<SuccessResponse<?>> getApplications(@PathVariable("id") final Long challengeId,
                                                              @RequestParam final Boolean isConfirmed) {
        GetChallengeApplicationsResponseDto responseDto = challengeService.getApplications(challengeId, isConfirmed);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 생성", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createChallengeProgram(@RequestBody final CreateChallengeRequestDto requestDto) {
        challengeService.createChallenge(requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "챌린지 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateChallengeProgram(@PathVariable("id") final Long challengeId,
                                                                     @RequestBody final CreateChallengeRequestDto requestDto) {
        challengeService.updateChallenge(challengeId, requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "챌린지 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> deleteChallengeProgram(@PathVariable("id") final Long challengeId) {
        challengeService.deleteChallenge(challengeId);
        return SuccessResponse.ok(null);
    }
}
