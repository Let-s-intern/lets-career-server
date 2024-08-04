package org.letscareer.letscareer.domain.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateRatingRequestDto;
import org.letscareer.letscareer.domain.blog.dto.response.rating.GetBlogRatingsResponseDto;
import org.letscareer.letscareer.domain.blog.dto.response.rating.GetRatingsResponseDto;
import org.letscareer.letscareer.domain.blog.service.RatingService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/blog-rating")
@RestController
public class RatingV1Controller {
    private final RatingService ratingService;

    @Operation(summary = "별점 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetRatingsResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getRatings(final Pageable pageable) {
        final GetRatingsResponseDto responseDto = ratingService.getRatings(pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "블로그 별 별점 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetBlogRatingsResponseDto.class)))
    })
    @GetMapping("/{blogId}")
    public ResponseEntity<SuccessResponse<?>> getBlogRatings(@PathVariable final Long blogId) {
        GetBlogRatingsResponseDto responseDto = ratingService.getBlogRatings(blogId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "별점 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping("/{blogId}")
    public ResponseEntity<SuccessResponse<?>> createBlogRating(@PathVariable final Long blogId,
                                                               @RequestBody final CreateRatingRequestDto requestDto) {
        ratingService.createBlogRating(blogId, requestDto);
        return SuccessResponse.created(null);
    }
}
