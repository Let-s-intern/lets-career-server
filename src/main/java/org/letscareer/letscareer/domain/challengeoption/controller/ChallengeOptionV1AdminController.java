package org.letscareer.letscareer.domain.challengeoption.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeoption.dto.request.CreateChallengeOptionRequestDto;
import org.letscareer.letscareer.domain.challengeoption.service.ChallengeOptionService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/challenge-option")
@RestController
public class ChallengeOptionV1AdminController {
    private final ChallengeOptionService challengeOptionService;

    @Operation(summary = "챌린지 옵션 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createChallengeOption(@RequestBody final CreateChallengeOptionRequestDto requestDto) {
        challengeOptionService.createChallengeOption(requestDto);
        return SuccessResponse.created(null);
    }
}
