package org.letscareer.letscareer.domain.challlengenotice.controller;

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

    @PostMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> createChallengeNotice(@PathVariable(name = "id") final Long challengeId,
                                                                    @RequestBody final CreateChallengeNoticeRequestDto createChallengeNoticeRequestDto) {
        challengeNoticeService.createChallengeNotice(challengeId, createChallengeNoticeRequestDto);
        return SuccessResponse.created(null);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<SuccessResponse<?>> getChallengeNoticesForAdmin(@PathVariable(name = "id") final Long challengeId) {
        return SuccessResponse.ok(challengeNoticeService.getChallengeNoticesForAdmin(challengeId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateChallengeNotice(@PathVariable(name = "id") final Long challengeNoticeId,
                                                                    @RequestBody final CreateChallengeNoticeRequestDto updateChallengeNoticeRequestDto) {
        challengeNoticeService.updateChallengeNotice(challengeNoticeId, updateChallengeNoticeRequestDto);
        return SuccessResponse.ok(null);
    }
}
