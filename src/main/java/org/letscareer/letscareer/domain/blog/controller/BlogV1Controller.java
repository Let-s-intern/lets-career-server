package org.letscareer.letscareer.domain.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.dto.request.UpdateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.dto.response.blog.GetBlogResponseDto;
import org.letscareer.letscareer.domain.blog.dto.response.blog.GetBlogsResponseDto;
import org.letscareer.letscareer.domain.blog.service.BlogService;
import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
@RestController
public class BlogV1Controller {
    private final BlogService blogService;

    @Operation(summary = "블로그 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetBlogsResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getBlogs(@RequestParam(required = false) final BlogType type,
                                                       @RequestParam(required = false) final Long tagId,
                                                       final Pageable pageable) {
        final GetBlogsResponseDto responseDto = blogService.getBlogs(type, tagId, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "블로그 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetBlogResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.BLOG_NOT_FOUND})
    @GetMapping("/{blogId}")
    public ResponseEntity<SuccessResponse<?>> getBlogDetail(@PathVariable final Long blogId) {
        final GetBlogResponseDto responseDto = blogService.getBlogDetail(blogId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "블로그 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.BLOG_NOT_FOUND})
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createBlog(@RequestBody final CreateBlogRequestDto requestDto) {
        blogService.createBlog(requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "블로그 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.BLOG_NOT_FOUND})
    @PatchMapping("/{blogId}")
    public ResponseEntity<SuccessResponse<?>> updateBlog(@PathVariable final Long blogId,
                                                         @RequestBody final UpdateBlogRequestDto requestDto) {
        blogService.updateBlog(blogId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "블로그 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.BLOG_NOT_FOUND})
    @DeleteMapping("/{blogId}")
    public ResponseEntity<SuccessResponse<?>> deleteBlog(@PathVariable final Long blogId) {
        blogService.deleteBlog(blogId);
        return SuccessResponse.ok(null);
    }
}
