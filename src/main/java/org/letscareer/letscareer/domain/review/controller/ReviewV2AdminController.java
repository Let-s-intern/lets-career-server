package org.letscareer.letscareer.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.dto.request.*;
import org.letscareer.letscareer.domain.review.dto.response.GetBlogReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.service.BlogReviewService;
import org.letscareer.letscareer.domain.review.service.ReviewItemService;
import org.letscareer.letscareer.domain.review.service.ReviewServiceFactory;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v2/admin/review")
@RestController
public class ReviewV2AdminController {
    private final ReviewServiceFactory reviewServiceFactory;
    private final ReviewItemService reviewItemService;
    private final BlogReviewService blogReviewService;

    @Operation(summary = "[어드민] 프로그램 참여 후기 전체 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReviewForAdminResponseDto.class)))
    })
    @GetMapping
    private ResponseEntity<SuccessResponse<?>> getReviewsForAdmin(@RequestParam final ReviewProgramType type) {
        GetReviewForAdminResponseDto responseDto = reviewServiceFactory.getReviewService(type).getReviewsForAdmin();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 블로그 후기 전체 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetBlogReviewForAdminResponseDto.class)))
    })
    @GetMapping("/blog")
    private ResponseEntity<SuccessResponse<?>> getBlogReviewsForAdmin() {
        GetBlogReviewForAdminResponseDto responseDto = blogReviewService.getBlogReviewForAdmin();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 블로그 후기 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping("/blog")
    private ResponseEntity<SuccessResponse<?>> createBlogReview(@RequestBody @Valid final CreateBlogReviewRequestDto requestDto) {
        blogReviewService.createBlogReview(requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[어드민] 프로그램 참여 후기 업데이트", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.REVIEW_NOT_FOUND})
    @PatchMapping("/{reviewId}")
    private ResponseEntity<SuccessResponse<?>> updateReview(@PathVariable final Long reviewId,
                                                            @RequestBody @Valid final UpdateReviewRequestDto requestDto) {
        reviewServiceFactory.getReviewService(requestDto.type()).updateReview(reviewId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[어드민] 블로그 후기 업데이트", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.BLOG_REVIEW_ITEM_NOT_FOUND})
    @PatchMapping("/blog/{blogReviewId}")
    private ResponseEntity<SuccessResponse<?>> updateBlogReview(@PathVariable final Long blogReviewId,
                                                                @RequestBody @Valid final UpdateBlogReviewRequestDto requestDto) {
        blogReviewService.updateBlogReview(blogReviewId, requestDto);
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

    @Operation(summary = "로직 X - [어드민] 블로그 후기 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/blog/{blogReviewId}")
    private ResponseEntity<SuccessResponse<?>> deleteBlogReview(@PathVariable final Long blogReviewId) {
        return SuccessResponse.ok(null);
    }
}
