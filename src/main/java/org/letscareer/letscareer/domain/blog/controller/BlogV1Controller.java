package org.letscareer.letscareer.domain.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateBlogRequestDto;
import org.letscareer.letscareer.domain.blog.dto.response.GetBlogResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeMyMissionDetailResponseDto;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
@RestController
public class BlogV1Controller {

    @Operation(summary = "블로그 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeMyMissionDetailResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getBlogs() {
        return null;
    }

    @Operation(summary = "블로그 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetBlogResponseDto.class)))
    })
    @GetMapping("/{blogId}")
    public ResponseEntity<SuccessResponse<?>> getBlogDetail(@PathVariable final Long blogId) {
        return null;
    }

    @Operation(summary = "블로그 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createBlog(@RequestBody final CreateBlogRequestDto requestDto) {
        return null;
    }


}
