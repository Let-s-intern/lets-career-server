package org.letscareer.letscareer.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.service.ReviewServiceFactory;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v2/review")
@RestController
public class ReviewV2Controller {
    private final ReviewServiceFactory reviewServiceFactory;

    @PostMapping
    private ResponseEntity<SuccessResponse<?>> createReview(@RequestParam final Long applicationId,
                                                            @RequestBody final CreateReviewRequestDto requestDto) {
        reviewServiceFactory.getReviewService(requestDto.programType()).createReview(applicationId, requestDto);
        return SuccessResponse.created(null);
    }
}
