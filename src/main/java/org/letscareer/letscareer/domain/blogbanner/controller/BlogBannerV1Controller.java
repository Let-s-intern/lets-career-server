package org.letscareer.letscareer.domain.blogbanner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetBlogBannersResponseDto;
import org.letscareer.letscareer.domain.blogbanner.service.BlogBannerService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@RequestMapping("/api/v1/blog-banner")
@RestController
public class BlogBannerV1Controller {
    private final BlogBannerService blogBannerService;

    @Operation(summary = "블로그 배너 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetBlogBannersResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getBlogBanners(final Pageable pageable) {
        GetBlogBannersResponseDto responseDto = blogBannerService.getBlogBanners(pageable);
        return SuccessResponse.ok(responseDto);
    }
}
