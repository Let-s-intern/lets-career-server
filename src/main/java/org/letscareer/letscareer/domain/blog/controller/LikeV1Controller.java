package org.letscareer.letscareer.domain.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.response.like.GetLikedBlogResponseDto;
import org.letscareer.letscareer.domain.blog.service.BlogLikeService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/blog-like")
@RestController
public class LikeV1Controller {
    private final BlogLikeService blogLikeService;

    @Operation(summary = "블로그 좋아요 클릭", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.BLOG_NOT_FOUND})
    @PostMapping("/{blogId}")
    public ResponseEntity<SuccessResponse<?>> addBlogLike(@CurrentUser final User user,
                                                          @PathVariable final Long blogId){
        blogLikeService.addBlogLike(blogId, user);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "좋아요한 블로그 조회", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getUserLikedBlogIds(@CurrentUser final User user) {
        final GetLikedBlogResponseDto responseDto = blogLikeService.getUserLikedBlogIds(user.getId());
        return SuccessResponse.ok(responseDto);
    }
}
