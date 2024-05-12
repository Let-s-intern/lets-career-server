package org.letscareer.letscareer.domain.challengeguide.controller;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeguide.dto.request.CreateChallengeGuideRequestDto;
import org.letscareer.letscareer.domain.challengeguide.service.ChallengeGuideService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge-guide")
@RestController
public class ChallengeGuideV1Controller {
    private final ChallengeGuideService challengeGuideService;

    @PostMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> createChallengeGuide(@PathVariable(name = "id") final Long challengeId,
                                                                   @RequestBody final CreateChallengeGuideRequestDto createChallengeGuideRequestDto) {
        challengeGuideService.createChallengeGuide(challengeId, createChallengeGuideRequestDto);
        return SuccessResponse.created(null);
    }
}
