package org.letscareer.letscareer.domain.live.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.response.GetLiveApplicationsResponseDto;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;
import org.letscareer.letscareer.domain.live.dto.request.CreateLiveRequestDto;
import org.letscareer.letscareer.domain.live.dto.request.UpdateLiveRequestDto;
import org.letscareer.letscareer.domain.live.dto.response.*;
import org.letscareer.letscareer.domain.live.service.LiveService;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/live")
@RestController
public class LiveV1Controller {
    private final LiveService liveService;

    @Operation(summary = "라이브 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetLivesResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getLiveList(@RequestParam(required = false) final List<ProgramClassification> typeList,
                                                          @RequestParam(required = false) final List<ProgramStatusType> statusList,
                                                          final Pageable pageable) {
        GetLivesResponseDto responseDto = liveService.getLiveList(typeList, statusList, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "라이브 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetLiveDetailResponseDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> getLiveDetail(@PathVariable("id") final Long liveId) {
        GetLiveDetailResponseDto responseDto = liveService.getLiveDetail(liveId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "라이브 섬네일 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetLiveThumbnailResponseDto.class)))
    })
    @GetMapping("/{id}/thumbnail")
    public ResponseEntity<SuccessResponse<?>> getLiveThumbnail(@PathVariable("id") final Long liveId) {
        final GetLiveThumbnailResponseDto responseDto = liveService.getLiveThumbnail(liveId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "라이브 상세내용 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetLiveContentResponseDto.class)))
    })
    @GetMapping("/{id}/content")
    public ResponseEntity<SuccessResponse<?>> getLiveDetailContent(@PathVariable("id") final Long liveId) {
        final GetLiveContentResponseDto responseDto = liveService.getLiveDetailContent(liveId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "라이브 faq 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetFaqResponseDto.class)))
    })
    @GetMapping("/{id}/faqs")
    public ResponseEntity<SuccessResponse<?>> getLiveFaqs(@PathVariable("id") final Long liveId) {
        final GetFaqResponseDto responseDto = liveService.getLiveFaqs(liveId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "라이브 신청폼 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetLiveApplicationFormResponseDto.class)))
    })
    @GetMapping("/{id}/application")
    public ResponseEntity<SuccessResponse<?>> getLiveApplicationForm(@PathVariable("id") final Long liveId,
                                                                     @CurrentUser User user) {
        final GetLiveApplicationFormResponseDto responseDto = liveService.getLiveApplicationForm(user, liveId);
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

    @Operation(summary = "신청자 리뷰 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetLiveReviewsResponseDto.class)))
    })
    @GetMapping("/{id}/reviews")
    public ResponseEntity<SuccessResponse<?>> getReviews(@PathVariable("id") final Long liveId,
                                                         final Pageable pageable) {
        final GetLiveReviewsResponseDto responseDto = liveService.getReviews(liveId, pageable);
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
                                                                     @RequestBody final UpdateLiveRequestDto requestDto) {
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
