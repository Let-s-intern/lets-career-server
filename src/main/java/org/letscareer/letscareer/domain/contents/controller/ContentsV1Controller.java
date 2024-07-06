package org.letscareer.letscareer.domain.contents.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.dto.request.CreateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.request.GetContentsResponseDto;
import org.letscareer.letscareer.domain.contents.dto.request.UpdateContentsRequestDto;
import org.letscareer.letscareer.domain.contents.dto.response.ContentsAdminListResponseDto;
import org.letscareer.letscareer.domain.contents.dto.response.ContentsAdminSimpleListResponseDto;
import org.letscareer.letscareer.domain.contents.service.ContentsService;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/contents")
@RestController
public class ContentsV1Controller {
    private final ContentsService contentsService;

    @Operation(summary = "콘텐츠 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetContentsResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.CONTENTS_NOT_FOUND})
    @GetMapping("/{contentsId}")
    public ResponseEntity<SuccessResponse<?>> getContentsDetail(@PathVariable final Long contentsId) {
        final GetContentsResponseDto responseDto = contentsService.getContentsDetail(contentsId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 콘텐츠 전체 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ContentsAdminListResponseDto.class)))
    })
    @GetMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> getAllContentsForAdmin(@PageableDefault Pageable pageable) {
        final ContentsAdminListResponseDto responseDto = contentsService.getAllContents(pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 콘텐츠 타입별 간단 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ContentsAdminSimpleListResponseDto.class)))
    })
    @GetMapping("/admin/simple")
    public ResponseEntity<SuccessResponse<?>> getAllSimpleContentsForAdmin(@RequestParam final ContentsType type) {
        final ContentsAdminSimpleListResponseDto responseDto = contentsService.getAllSimpleContents(type);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 콘텐츠 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createContents(@RequestBody @Valid final CreateContentsRequestDto createContentsRequestDto) {
        contentsService.createContents(createContentsRequestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[어드민] 콘텐츠 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{contentsId}")
    public ResponseEntity<SuccessResponse<?>> updateContents(@PathVariable final Long contentsId,
                                                             @RequestBody final UpdateContentsRequestDto updateContentsRequestDto) {
        contentsService.updateContents(contentsId, updateContentsRequestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[어드민] 콘텐츠 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{contentsId}")
    public ResponseEntity<SuccessResponse<?>> deleteContents(@PathVariable final Long contentsId) {
        contentsService.deleteContents(contentsId);
        return SuccessResponse.ok(null);
    }
}
