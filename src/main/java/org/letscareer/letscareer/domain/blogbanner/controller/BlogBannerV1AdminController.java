package org.letscareer.letscareer.domain.blogbanner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blogbanner.dto.request.CreateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.dto.request.UpdateBlogBannerRequestDto;
import org.letscareer.letscareer.domain.blogbanner.dto.response.GetAdminBlogBannersResponseDto;
import org.letscareer.letscareer.domain.blogbanner.service.BlogBannerService;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/blog-banner")
@RestController
public class BlogBannerV1AdminController {
    private final BlogBannerService blogBannerService;

    @Operation(summary = "블로그 배너 목록 조회", responses = {
            @ApiResponse(responseCode = "200",content = @Content(schema = @Schema(implementation = GetAdminBlogBannersResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getBlogBanners(){
        GetAdminBlogBannersResponseDto responseDto = blogBannerService.getAdminBlogBanners();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "블로그 배너 생성", responses = {@ApiResponse(responseCode = "201", useReturnTypeSchema = true)})
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createBlogBanner(@RequestBody @Valid final CreateBlogBannerRequestDto requestDto){
        blogBannerService.createBlogBanner(requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "블로그 배너 수정", responses = {@ApiResponse(responseCode = "200", useReturnTypeSchema = true)})
    @ApiErrorCode(SwaggerEnum.BLOG_BANNER_NOT_FOUND)
    @PatchMapping("/{blogBannerId}")
    public ResponseEntity<SuccessResponse<?>> updateBlogBanner(@PathVariable final Long blogBannerId,
                                                               @RequestBody final UpdateBlogBannerRequestDto requestDto){
        blogBannerService.updateBlogBanner(blogBannerId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "블로그 배너 삭제", responses = {@ApiResponse(responseCode = "200", useReturnTypeSchema = true)})
    @ApiErrorCode(SwaggerEnum.BLOG_BANNER_NOT_FOUND)
    @DeleteMapping("/{blogBannerId}")
    public ResponseEntity<SuccessResponse<?>> deleteBlogBanner(@PathVariable final Long blogBannerId){
        blogBannerService.deleteBlogBanner(blogBannerId);
        return SuccessResponse.ok(null);
    }
}
