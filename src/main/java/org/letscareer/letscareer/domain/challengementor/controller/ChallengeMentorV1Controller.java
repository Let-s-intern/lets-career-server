package org.letscareer.letscareer.domain.challengementor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengementor.dto.response.GetMyChallengeMentorsResponseDto;
import org.letscareer.letscareer.domain.challengementor.service.ChallengeMentorService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge-mentor")
@RestController
public class ChallengeMentorV1Controller {
    private final ChallengeMentorService challengeMentorService;

    @Operation(summary = "멘토 1명의 챌린지 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetMyChallengeMentorsResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.NOT_CHALLENGE_MENTOR})
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getMyChallengeMentors(@CurrentUser final User mentor) {
        GetMyChallengeMentorsResponseDto responseDto = challengeMentorService.getMyChallengeMentors(mentor);
        return SuccessResponse.ok(responseDto);
    }
}
