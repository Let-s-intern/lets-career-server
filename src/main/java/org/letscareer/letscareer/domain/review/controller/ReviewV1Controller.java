package org.letscareer.letscareer.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.service.ReviewService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
@RestController
public class ReviewV1Controller {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createReview(@RequestParam final Long applicationId,
                                                           @RequestBody final CreateReviewRequestDto requestDto) {
        reviewService.createReview(applicationId, requestDto);
        return SuccessResponse.ok(null);
    }
}
