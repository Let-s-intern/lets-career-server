package org.letscareer.letscareer.domain.vod.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.live.dto.response.GetLivesResponseDto;
import org.letscareer.letscareer.domain.vod.dto.request.CreateVodRequestDto;
import org.letscareer.letscareer.domain.vod.dto.response.GetVodDetailResponseDto;
import org.letscareer.letscareer.domain.vod.dto.response.GetVodsResponseDto;
import org.letscareer.letscareer.domain.vod.service.VodService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/vod")
@RestController
public class VodV1Controller {
    private final VodService vodService;

    @Operation(summary = "vod 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetVodsResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getVodList(@RequestParam(required = false) final ProgramClassification type,
                                                         final Pageable pageable) {
        GetVodsResponseDto responseDto = vodService.getVodList(type, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "vod 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetVodDetailResponseDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> getVodDetail(@PathVariable("id") final Long vodId) {
        GetVodDetailResponseDto responseDto = vodService.getVodDetail(vodId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "vod 생성", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createVodProgram(@RequestBody final CreateVodRequestDto requestDto) {
        vodService.createVod(requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "vod 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateVodProgram(@PathVariable("id") final Long vodId,
                                                               @RequestBody final CreateVodRequestDto requestDto) {
        vodService.updateVod(vodId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "vod 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> deleteVodProgram(@PathVariable("id") final Long vodId) {
        vodService.deleteVod(vodId);
        return SuccessResponse.ok(null);
    }
}
