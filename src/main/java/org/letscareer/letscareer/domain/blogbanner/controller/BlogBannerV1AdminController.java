package org.letscareer.letscareer.domain.blogbanner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.service.BlogBannerService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/blog-banner")
@RestController
public class BlogBannerV1AdminController {
    private final BlogBannerService blogBannerService;

    @Operation(summary = "블로그 배너 생성", responses = {@ApiResponse(responseCode = "201", useReturnTypeSchema = true)})
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createBlogBanner(@RequestBody @Valid final CreateBlogBannerRequestDto requestDto){
        blogBannerService.createBlogBanner(requestDto);
        return SuccessResponse.created(null);
    }
}
