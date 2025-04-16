package org.letscareer.letscareer.domain.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeMissionAttendancesResponseDto;
import org.letscareer.letscareer.domain.challenge.service.ChallengeService;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v2/admin/challenge")
@RestController
public class ChallengeV2AdminController {
    public final ChallengeService challengeService;

    @Operation(summary = "챌린지 대시보드 복제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.CHALLENGE_NOT_FOUND})
    @GetMapping("/copy-dashboard/{fromChallengeId}/{toChallengeId}")
    public ResponseEntity<SuccessResponse<?>> copyChallengeDashBoard(@PathVariable final Long fromChallengeId,
                                                                     @PathVariable final Long toChallengeId) {
        challengeService.copyChallengeDashBoard(fromChallengeId, toChallengeId);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "챌린지 참여자 목록 조회", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true,
            content = @Content(schema = @Schema(implementation = GetChallengeApplicationsResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.CHALLENGE_NOT_FOUND})
    @GetMapping("/{challengeId}/applications")
    public ResponseEntity<SuccessResponse<?>> getChallengeApplications(@PathVariable final Long challengeId,
                                                                       @RequestParam(required = false) final Boolean isCanceled) {
        final GetChallengeApplicationsResponseDto responseDto = challengeService.getApplications(challengeId, isCanceled);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 미션별 제출자 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeMissionAttendancesResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.PAYMENT_NOT_FOUND})
    @GetMapping("/{challengeId}/mission/{missionId}/attendances")
    public ResponseEntity<SuccessResponse<?>> getMissionAttendances(@PathVariable final Long challengeId,
                                                                    @PathVariable final Long missionId) {
        final GetChallengeMissionAttendancesResponseDto responseDto = challengeService.getMissionAttendances(challengeId, missionId);
        return SuccessResponse.ok(responseDto);
    }
}
