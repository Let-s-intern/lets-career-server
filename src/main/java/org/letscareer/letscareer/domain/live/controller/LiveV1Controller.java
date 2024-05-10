package org.letscareer.letscareer.domain.live.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.response.GetLiveApplicationsResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.dto.response.GetLiveDetailResponseDto;
import org.letscareer.letscareer.domain.live.service.LiveService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/live")
@RestController
public class LiveV1Controller {
    private final LiveService liveService;

    @Operation(summary = "라이브 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetLiveDetailResponseDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> getLiveDetail(@PathVariable("id") final Long liveId) {
        GetLiveDetailResponseDto responseDto = liveService.getLiveDetail(liveId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "프로그램 신청자 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetLiveApplicationsResponseDto.class)))
    })
    @GetMapping("/{id}/applications")
    public ResponseEntity<SuccessResponse<?>> getApplications(@PathVariable("id") final Long liveId,
                                                              @RequestParam(required = false) final Boolean isConfirmed) {
        GetLiveApplicationsResponseDto responseDto = liveService.getApplications(liveId, isConfirmed);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "라이브 생성", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createLiveProgram(@RequestBody final CreateLiveRequestDto requestDto) {
        liveService.createLive(requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "라이브 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateChallengeProgram(@PathVariable("id") final Long liveId,
                                                                     @RequestBody final CreateLiveRequestDto requestDto) {
        liveService.updateLive(liveId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "라이브 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> deleteLiveProgram(@PathVariable("id") final Long liveId) {
        liveService.deleteLive(liveId);
        return SuccessResponse.ok(null);
    }
}
