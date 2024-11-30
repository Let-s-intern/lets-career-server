package org.letscareer.letscareer.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewDetailListResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewDetailResponseDto;
import org.letscareer.letscareer.domain.review.service.ReviewService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
@RestController
public class ReviewV1Controller {
    private final ReviewService reviewService;

    @Operation(summary = "프로그램 유형 별 리뷰 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReviewDetailListResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getReviewDetailList(@RequestParam("type") final ProgramType programType) {
        GetReviewDetailListResponseDto responseDto = reviewService.getReviewDetailList(programType);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "리뷰 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReviewDetailResponseDto.class)))
    })
    @GetMapping("/{reviewId}")
    public ResponseEntity<SuccessResponse<?>> getReviewDetail(@PathVariable final Long reviewId) {
        GetReviewDetailResponseDto responseDto = reviewService.getReviewDetail(reviewId);
        return SuccessResponse.ok(responseDto);
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

    @Operation(summary = "리뷰 생성 - 링크", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.BAD_REQUEST})
    @PostMapping("/{programId}")
    public ResponseEntity<SuccessResponse<?>> createReviewByLink(@PathVariable final Long programId,
                                                                 @RequestParam("type") final ProgramType programType,
                                                                 @Valid @RequestBody final CreateReviewRequestDto requestDto) {
        reviewService.createReviewByLink(programId, programType, requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "리뷰 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{reviewId}")
    public ResponseEntity<SuccessResponse<?>> updateReview(@PathVariable final Long reviewId,
                                                           @RequestBody final UpdateReviewRequestDto requestDto) {
        reviewService.updateReview(reviewId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[어드민] 리뷰 노출 여부 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{reviewId}/status")
    public ResponseEntity<SuccessResponse<?>> updateReviewVisibleStatus(@PathVariable final Long reviewId,
                                                                        @RequestParam final Boolean isVisible) {
        reviewService.updateReviewVisibleStatus(reviewId, isVisible);
        return SuccessResponse.ok(null);
    }
}
