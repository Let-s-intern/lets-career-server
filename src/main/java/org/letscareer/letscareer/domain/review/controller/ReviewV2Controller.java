package org.letscareer.letscareer.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.dto.request.*;
import org.letscareer.letscareer.domain.review.dto.response.GetBlogReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetBlogReviewResponseDto;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v2/review")
@RestController
public class ReviewV2Controller {
    private final ReviewServiceFactory reviewServiceFactory;
    private final VWReviewService reviewService;
    private final ReviewItemService reviewItemService;

    @Operation(
            summary = "프로그램 참여 후기 전체 조회 (challenge, mission, live, report)",
            description = """
                    [100% 솔직 후기 > 프로그램 참여 후기]
                    - 챌린지 & 전체 : type=CHALLENGE_REVIEW & type=MISSION_REVIEW <br>
                    - 챌린지 & 미션 수행 후기 : type=MISSION_REVIEW <br>
                    - 챌린지 & 프로그램 참여 후기 : type=CHALLENGE_REVIEW <br>
                    """,
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReviewResponseDto.class)))
    })
    @GetMapping
    private ResponseEntity<SuccessResponse<?>> getReviews(@RequestParam(required = false) final List<ReviewProgramType> type,
                                                          @RequestParam(required = false) final List<ChallengeType> challengeType,
                                                          final Pageable pageable) {
        GetReviewResponseDto responseDto = reviewService.getReviews(type, challengeType, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(
            summary = "로직 X - 블로그 후기 전체 조회",
            description = "[100% 솔직 후기 > 블로그 후기]",
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetBlogReviewResponseDto.class)))
            })
    @GetMapping("/blog")
    private ResponseEntity<SuccessResponse<?>> getBlogReviews(@RequestParam(required = false) final List<ProgramType> type,
                                                              final Pageable pageable) {
        GetBlogReviewResponseDto responseDto = null;
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 프로그램 참여 후기 전체 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReviewForAdminResponseDto.class)))
    })
    @GetMapping("/admin")
    private ResponseEntity<SuccessResponse<?>> getReviewsForAdmin(@RequestParam final ReviewProgramType type) {
        GetReviewForAdminResponseDto responseDto = reviewServiceFactory.getReviewService(type).getReviewsForAdmin();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "로직 X - [어드민] 블로그 후기 전체 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetBlogReviewForAdminResponseDto.class)))
    })
    @GetMapping("/admin/blog")
    private ResponseEntity<SuccessResponse<?>> getBlogReviewsForAdmin() {
        GetBlogReviewForAdminResponseDto responseDto = null;
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "프로그램 참여 후기 생성 (challenge, live, report)", responses = {
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

    @Operation(summary = "로직 X - [어드민] 블로그 후기 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping("/blog")
    private ResponseEntity<SuccessResponse<?>> createBlogReview(@RequestBody @Valid final CreateBlogReviewRequestDto requestDto) {
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

    @Operation(summary = "로직 X - [어드민] 블로그 후기 업데이트", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/blog/{blogReviewId}")
    private ResponseEntity<SuccessResponse<?>> updateBlogReview(@PathVariable final Long blogReviewId,
                                                                @RequestBody @Valid final UpdateBlogReviewRequestDto requestDto) {
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
