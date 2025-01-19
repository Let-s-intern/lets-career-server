package org.letscareer.letscareer.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.service.ReviewServiceFactory;
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
}
