package org.letscareer.letscareer.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewItemRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.letscareer.letscareer.domain.review.service.ReviewItemService;
import org.letscareer.letscareer.domain.review.service.ReviewServiceFactory;
import org.letscareer.letscareer.domain.review.service.VWReviewService;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v2/review")
@RestController
public class ReviewV2Controller {
    private final ReviewServiceFactory reviewServiceFactory;
    private final VWReviewService reviewService;
    private final ReviewItemService reviewItemService;

    @Operation(summary = "프로그램 참여 후기 전체 조회", description = "[100% 솔직 후기]", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReviewResponseDto.class)))
    })
    @GetMapping
    private ResponseEntity<SuccessResponse<?>> getReviews() {
        GetReviewResponseDto responseDto = reviewService.getReviews();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 리뷰 전체 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReviewForAdminResponseDto.class)))
    })
    @GetMapping("/admin")
    private ResponseEntity<SuccessResponse<?>> getReviewsForAdmin(@RequestParam final ReviewProgramType type) {
        GetReviewForAdminResponseDto responseDto = reviewServiceFactory.getReviewService(type).getReviewsForAdmin();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "리뷰 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.UNAUTHORIZED, SwaggerEnum.REVIEW_NOT_FOUND, SwaggerEnum.REVIEW_ALREADY_EXISTS})
    @PostMapping
    private ResponseEntity<SuccessResponse<?>> createReview(@RequestParam final Long applicationId,
                                                            @RequestBody @Valid final CreateReviewRequestDto requestDto,
                                                            @CurrentUser final User user) {
        reviewServiceFactory.getReviewService(requestDto.type()).createReview(user, applicationId, requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[어드민] 리뷰 업데이트", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.REVIEW_NOT_FOUND})
    @PatchMapping("/{reviewId}")
    private ResponseEntity<SuccessResponse<?>> updateReview(@PathVariable final Long reviewId,
                                                            @RequestBody @Valid final UpdateReviewRequestDto requestDto) {
        reviewServiceFactory.getReviewService(requestDto.type()).updateReview(reviewId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[어드민] 리뷰 아이템 업데이트", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.REVIEW_ITEM_NOT_FOUND})
    @PatchMapping("/item/{reviewItemId}")
    private ResponseEntity<SuccessResponse<?>> updateReviewItem(@PathVariable final Long reviewItemId,
                                                                @RequestBody @Valid final UpdateReviewItemRequestDto requestDto) {
        reviewItemService.updateReviewItem(reviewItemId, requestDto);
        return SuccessResponse.ok(null);
    }
}
