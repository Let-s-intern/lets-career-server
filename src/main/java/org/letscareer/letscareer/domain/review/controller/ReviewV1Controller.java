package org.letscareer.letscareer.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewDetailResponseDto;
import org.letscareer.letscareer.domain.review.service.ReviewService;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
@RestController
public class ReviewV1Controller {
    private final ReviewService reviewService;

    @Operation(summary = "리뷰 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReviewDetailResponseDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> getReviewDetail(@PathVariable("id") final Long reviewId) {
        GetReviewDetailResponseDto requestDto = reviewService.getReviewDetail(reviewId);
        return SuccessResponse.ok(requestDto);
    }

    @Operation(summary = "리뷰 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.BAD_REQUEST})
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createReview(@RequestParam final Long applicationId,
                                                           @Valid @RequestBody final CreateReviewRequestDto requestDto) {
        reviewService.createReview(applicationId, requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "리뷰 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateReview(@PathVariable("id") final Long reviewId,
                                                           @RequestBody final UpdateReviewRequestDto requestDto) {
        reviewService.updateReview(reviewId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[어드민] 리뷰 노출 여부 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<SuccessResponse<?>> updateReviewVisibleStatus(@PathVariable("id") final Long reviewId,
                                                                        @RequestParam final Boolean isVisible) {
        reviewService.updateReviewVisibleStatus(reviewId, isVisible);
        return SuccessResponse.ok(null);
    }
}
