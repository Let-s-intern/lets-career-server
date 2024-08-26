package org.letscareer.letscareer.domain.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportApplicationRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.UpdateReportRequestDto;
import org.letscareer.letscareer.domain.report.dto.res.*;
import org.letscareer.letscareer.domain.report.service.*;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.letscareer.letscareer.global.common.entity.SwaggerEnum.REPORT_APPLICATION_NOT_FOUND;
import static org.letscareer.letscareer.global.common.entity.SwaggerEnum.REPORT_NOT_FOUND;

@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
@RestController
public class ReportV1Controller {
    private final GetReportsForAdminService getReportsForAdminService;
    private final GetReportDetailForAdminService getReportDetailForAdminService;
    private final GetReportApplicationsForAdminService getReportApplicationsForAdminService;
    private final GetReportApplicationPaymentForAdminService getReportApplicationPaymentForAdminService;
    private final GetReportDetailService getReportDetailService;
    private final GetReportPriceDetailService getReportPriceDetailService;
    private final GetMyReportService getMyReportService;
    private final GetMyReportFeedbackService getMyReportFeedbackService;
    private final CreateReportService createReportService;
    private final CreateReportApplicationService createReportApplicationService;
    private final CancelReportApplicationService cancelReportApplicationService;
    private final GetReportThumbnailService getReportThumbnailService;
    private final UpdateReportService updateReportService;
    private final DeleteReportService deleteReportService;

    @Operation(summary = "어드민 - 진단서 목록 조회")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportsForAdminResponseDto.class)))
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getReportsForAdmin(final Pageable pageable) {
        final GetReportsForAdminResponseDto responseDto = getReportsForAdminService.execute(pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[테스트 중] 어드민 - 진단서 상세 조회")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportDetailForAdminResponseDto.class)))
    @ApiErrorCode({REPORT_NOT_FOUND})
    @GetMapping("/{reportId}/admin")
    public ResponseEntity<SuccessResponse<?>> getReportDetailForAdmin(@PathVariable final Long reportId) {
        final GetReportDetailForAdminResponseDto responseDto = getReportDetailForAdminService.execute(reportId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[테스트 중] 어드민 - 진단서 참여자 목록 조회")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportApplicationsForAdminResponseDto.class)))
    @GetMapping("/applications")
    public ResponseEntity<SuccessResponse<?>> getReportApplicationsForAdmin(@RequestParam(required = false) final Long reportId,
                                                                            @RequestParam(required = false) final ReportPriceType priceType,
                                                                            final Pageable pageable) {
        final GetReportApplicationsForAdminResponseDto responseDto = getReportApplicationsForAdminService.execute(reportId, priceType, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[테스트 중] 어드민 - 진단서 참여자 옵션")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportApplicationOptionsForAdminResponseDto.class)))
    @ApiErrorCode({REPORT_APPLICATION_NOT_FOUND})
    @GetMapping("/application/options")
    public ResponseEntity<SuccessResponse<?>> getReportApplicationPaymentForAdmin(@RequestParam(required = false) final Long reportId,
                                                                                  @RequestParam(required = false) final Long applicationId,
                                                                                  @RequestParam(required = false) final ReportPriceType priceType,
                                                                                  @RequestParam(required = false) final String code) {
        final GetReportApplicationOptionsForAdminResponseDto responseDto = getReportApplicationPaymentForAdminService.execute(reportId, applicationId, priceType, code);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[테스트 중] 유저 - 진단서 상세 조회", description = "[서류 진단 신청하기]")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportDetailResponseDto.class)))
    @ApiErrorCode({REPORT_NOT_FOUND})
    @GetMapping("/{reportId}")
    public ResponseEntity<SuccessResponse<?>> getReportDetail(@PathVariable final Long reportId) {
        final GetReportDetailResponseDto responseDto = getReportDetailService.execute(reportId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[테스트 중] 유저 - 홈화면 조회", description = "[홈화면]")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportThumbnailResponseDto.class)))
    @ApiErrorCode({REPORT_NOT_FOUND})
    @GetMapping("/thumbnail")
    public ResponseEntity<SuccessResponse<?>> getReportThumbnail() {
        final GetReportThumbnailResponseDto responseDto = getReportThumbnailService.execute();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[테스트 중] 유저 - 진단서 가격 상세 정보", description = "[서류 진단 신청하기 -> 하단 모달]")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportPriceDetailResponseDto.class)))
    @ApiErrorCode({REPORT_NOT_FOUND})
    @GetMapping("/{reportId}/price")
    public ResponseEntity<SuccessResponse<?>> getReportPriceDetail(@PathVariable final Long reportId) {
        final GetReportPriceDetailResponseDto responseDto = getReportPriceDetailService.execute(reportId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[테스트 중] 유저 - 나의 진단서 목록", description = "[My 진단서 보기 -> 서류 진단서] reportType을 제외할 경우 전체 조회")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetMyReportResponseDto.class)))
    @GetMapping("/my")
    public ResponseEntity<SuccessResponse<?>> getMyReports(@CurrentUser final User user,
                                                           @RequestParam(required = false) final ReportType reportType,
                                                           final Pageable pageable) {
        final GetMyReportResponseDto responseDto = getMyReportService.execute(user, reportType, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[테스트 중] 나의 1:1 첨삭 목록 보기", description = "[My 진단서 보기 -> 맞춤 첨삭] reportType을 제외할 경우 전체 조회")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetMyReportFeedbackResponseDto.class)))
    @GetMapping("/my/feedback")
    public ResponseEntity<SuccessResponse<?>> getMyReportFeedbacks(@CurrentUser final User user,
                                                                   @RequestParam(required = false) final ReportType reportType,
                                                                   final Pageable pageable) {
        final GetMyReportFeedbackResponseDto responseDto = getMyReportFeedbackService.execute(user, reportType, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "진단서 프로그램 생성")
    @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createReport(@RequestBody final CreateReportRequestDto requestDto) {
        createReportService.execute(requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[테스트 중] 진단서 신청")
    @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    @PostMapping("/application")
    public ResponseEntity<SuccessResponse<?>> createReportApplication(@CurrentUser final User user,
                                                                      @RequestBody final CreateReportApplicationRequestDto requestDto) {
        createReportApplicationService.execute(user, requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[테스트 중] 진단서 신청 취소")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @DeleteMapping("/application/{reportApplicationId}")
    public ResponseEntity<SuccessResponse<?>> cancelReportApplication(@CurrentUser final User user,
                                                                      @PathVariable final Long reportApplicationId) {
        cancelReportApplicationService.execute(user, reportApplicationId);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "진단서 프로그램 수정")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @PatchMapping("/{reportId}")
    public ResponseEntity<SuccessResponse<?>> updateReport(@PathVariable final Long reportId,
                                                           @RequestBody final UpdateReportRequestDto requestDto) {
        updateReportService.execute(reportId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "진단서 프로그램 삭제")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @DeleteMapping("/{reportId}")
    public ResponseEntity<SuccessResponse<?>> deleteReport(@PathVariable final Long reportId) {
        deleteReportService.execute(reportId);
        return ResponseEntity.ok(null);
    }
}
