package org.letscareer.letscareer.domain.challengementor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengementor.service.ChallengeMentorService;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/challenge-mentor")
@RestController
public class ChallengeMentorV1AdminController {
    private final ChallengeMentorService challengeMentorService;

    @Operation(summary = "챌린지 멘토 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.CHALLENGE_NOT_FOUND})
    @DeleteMapping("/{challengeMentorId}")
    public ResponseEntity<SuccessResponse<?>> deleteChallengeMentor(@PathVariable final Long challengeMentorId) {
        challengeMentorService.deleteChallengeMentor(challengeMentorId);
        return SuccessResponse.ok(null);
    }
}
