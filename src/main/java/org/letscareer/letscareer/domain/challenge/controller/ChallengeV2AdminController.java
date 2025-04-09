package org.letscareer.letscareer.domain.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
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
}
