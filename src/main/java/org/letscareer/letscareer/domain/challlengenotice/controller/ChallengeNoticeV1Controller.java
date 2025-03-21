package org.letscareer.letscareer.domain.challlengenotice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challlengenotice.dto.request.CreateChallengeNoticeRequestDto;
import org.letscareer.letscareer.domain.challlengenotice.service.ChallengeNoticeService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge-notice")
@RestController
public class ChallengeNoticeV1Controller {
    private final ChallengeNoticeService challengeNoticeService;

    @Operation(summary = "[어드민] 챌린지 공지사항 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping("/{challengeId}")
    public ResponseEntity<SuccessResponse<?>> createChallengeNotice(@PathVariable final Long challengeId,
                                                                    @RequestBody final CreateChallengeNoticeRequestDto createChallengeNoticeRequestDto) {
        challengeNoticeService.createChallengeNotice(challengeId, createChallengeNoticeRequestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[어드민] 챌린지 공지사항 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{challengeNoticeId}")
    public ResponseEntity<SuccessResponse<?>> updateChallengeNotice(@PathVariable final Long challengeNoticeId,
                                                                    @RequestBody final CreateChallengeNoticeRequestDto updateChallengeNoticeRequestDto) {
        challengeNoticeService.updateChallengeNotice(challengeNoticeId, updateChallengeNoticeRequestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[어드민] 챌린지 공지사항 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{challengeNoticeId}")
    public ResponseEntity<SuccessResponse<?>> deleteChallengeNotice(@PathVariable final Long challengeNoticeId) {
        challengeNoticeService.deleteChallengeNotice(challengeNoticeId);
        return SuccessResponse.ok(null);
    }
}
