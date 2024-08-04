package org.letscareer.letscareer.domain.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.dto.request.CreateHashTagRequestDto;
import org.letscareer.letscareer.domain.blog.dto.request.UpdateHashTagRequestDto;
import org.letscareer.letscareer.domain.blog.dto.response.tag.CreateTagResponseDto;
import org.letscareer.letscareer.domain.blog.dto.response.tag.GetTagsResponseDto;
import org.letscareer.letscareer.domain.blog.service.HashTagService;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeMyMissionDetailResponseDto;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/blog-tag")
@RestController
public class HashTagV1Controller {

    private final HashTagService hashTagService;

    @Operation(summary = "태그 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetTagsResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getTags() {
        GetTagsResponseDto responseDto = hashTagService.getTags();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "태그 생성", responses = {
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = CreateTagResponseDto.class)))
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createHashTag(@RequestBody final CreateHashTagRequestDto requestDto) {
        CreateTagResponseDto responseDto = hashTagService.createHashTag(requestDto);
        return SuccessResponse.created(responseDto);
    }

    @Operation(summary = "태그 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PostMapping("/{tagId}")
    public ResponseEntity<SuccessResponse<?>> updateHashTag(@PathVariable final Long tagId,
                                                            @RequestBody final UpdateHashTagRequestDto requestDto) {
        hashTagService.updateHashTag(tagId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "태그 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.HASHTAG_CANNOT_DELETED})
    @DeleteMapping("/{tagId}")
    public ResponseEntity<SuccessResponse<?>> deleteHashTag(@PathVariable final Long tagId) {
        hashTagService.deleteHashTag(tagId);
        return SuccessResponse.ok(null);
    }
}
