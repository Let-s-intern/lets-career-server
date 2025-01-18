package org.letscareer.letscareer.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.dto.request.CreateOldReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateOldReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewDetailListResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewDetailResponseDto;
import org.letscareer.letscareer.domain.review.service.OldReviewService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
@RestController
public class ReviewV1Controller {
    private final OldReviewService oldReviewService;

    @Operation(summary = "[old] 프로그램 유형 별 리뷰 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetOldReviewDetailListResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getReviewDetailList(@CurrentUser User user,
                                                                  @RequestParam("type") final ProgramType programType,
                                                                  @RequestParam(value = "sort", defaultValue = "id;DESC", required = false) List<String> sortBy) {
        GetOldReviewDetailListResponseDto responseDto = oldReviewService.getReviewDetailList(user, programType, sortBy);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[old] 리뷰 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetOldReviewDetailResponseDto.class)))
    })
    @GetMapping("/{reviewId}")
    public ResponseEntity<SuccessResponse<?>> getReviewDetail(@PathVariable final Long reviewId) {
        GetOldReviewDetailResponseDto responseDto = oldReviewService.getReviewDetail(reviewId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[old] 리뷰 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.BAD_REQUEST})
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createReview(@RequestParam final Long applicationId,
                                                           @Valid @RequestBody final CreateOldReviewRequestDto requestDto) {
        oldReviewService.createReview(applicationId, requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[old] 리뷰 생성 - 링크", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.BAD_REQUEST})
    @PostMapping("/{programId}")
    public ResponseEntity<SuccessResponse<?>> createReviewByLink(@PathVariable final Long programId,
                                                                 @RequestParam("type") final ProgramType programType,
                                                                 @Valid @RequestBody final CreateOldReviewRequestDto requestDto) {
        oldReviewService.createReviewByLink(programId, programType, requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[old] 리뷰 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{reviewId}")
    public ResponseEntity<SuccessResponse<?>> updateReview(@PathVariable final Long reviewId,
                                                           @RequestBody final UpdateOldReviewRequestDto requestDto) {
        oldReviewService.updateReview(reviewId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[old] 어드민 리뷰 노출 여부 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{reviewId}/status")
    public ResponseEntity<SuccessResponse<?>> updateReviewVisibleStatus(@PathVariable final Long reviewId,
                                                                        @RequestParam final Boolean isVisible) {
        oldReviewService.updateReviewVisibleStatus(reviewId, isVisible);
        return SuccessResponse.ok(null);
    }
}
