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
import org.letscareer.letscareer.domain.review.dto.response.CreateUserBlogReviewResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetBlogReviewResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetMyReviewResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewCountResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.letscareer.letscareer.domain.review.service.BlogReviewService;
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
    private final BlogReviewService blogReviewService;

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
                                                          @RequestParam(required = false) final List<String> liveJob,
                                                          final Pageable pageable) {
        GetReviewResponseDto responseDto = reviewService.getReviews(type, challengeType, liveJob, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(
            summary = "프로그램 참여 후기 1건 조회",
            description = "[마이페이지 > 후기 작성]",
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetMyReviewResponseDto.class)))}
    )
    @GetMapping("/{type}/{reviewId}")
    private ResponseEntity<SuccessResponse<?>> getReview(@PathVariable final ReviewProgramType type,
                                                         @PathVariable final Long reviewId,
                                                         @CurrentUser final User user) {
        GetMyReviewResponseDto responseDto = reviewServiceFactory.getReviewService(type).getReview(reviewId, user);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(
            summary = "블로그 후기 전체 조회",
            description = "[100% 솔직 후기 > 블로그 후기]",
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetBlogReviewResponseDto.class)))}
    )
    @GetMapping("/blog")
    private ResponseEntity<SuccessResponse<?>> getBlogReviews(@RequestParam(required = false) final List<ProgramType> type,
                                                              final Pageable pageable) {
        GetBlogReviewResponseDto responseDto = blogReviewService.getBlogReviews(type, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(
            summary = "후기 전체 개수 조회",
            description = "[100% 솔직 후기 > 배너]",
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReviewCountResponseDto.class)))}
    )
    @GetMapping("/count")
    private ResponseEntity<SuccessResponse<?>> getReviewCount() {
        GetReviewCountResponseDto responseDto = reviewService.getReviewCount();
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

    @Operation(
            summary = "보너스 미션 블로그 후기 제출",
            description = "참여자가 보너스 미션의 블로그 후기 URL을 제출하면 자동으로 BlogReview와 Attendance가 생성됩니다.",
            responses = {@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = CreateUserBlogReviewResponseDto.class)))}
    )
    @PostMapping("/blog/bonus")
    private ResponseEntity<SuccessResponse<?>> createUserBlogReview(@RequestBody @Valid final CreateUserBlogReviewRequestDto requestDto,
                                                                   @CurrentUser final User user) {
        CreateUserBlogReviewResponseDto responseDto = blogReviewService.createUserBlogReview(requestDto, user);
        return SuccessResponse.created(responseDto);
    }
}
