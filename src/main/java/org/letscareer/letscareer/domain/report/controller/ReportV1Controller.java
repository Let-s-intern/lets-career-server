package org.letscareer.letscareer.domain.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.res.*;
import org.letscareer.letscareer.domain.report.service.*;
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
    private final GetReportFeedbackApplicationsForAdminService getReportFeedbackApplicationsForAdminService;
    private final GetReportApplicationPaymentForAdminService getReportApplicationPaymentForAdminService;
    private final GetReportDetailService getReportDetailService;
    private final GetReportPriceDetailService getReportPriceDetailService;
    private final GetMyReportService getMyReportService;

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
    @GetMapping("/{reportId}/applications")
    public ResponseEntity<SuccessResponse<?>> getReportApplicationsForAdmin(@PathVariable final Long reportId,
                                                                            final Pageable pageable) {
        final GetReportApplicationsForAdminResponseDto responseDto = getReportApplicationsForAdminService.execute(reportId, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[테스트 중] 어드민 - 1:1 첨삭 참여자 목록 조회")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportFeedbackApplicationsForAdminResponseDto.class)))
    @GetMapping("/{reportId}/feedback/applications")
    public ResponseEntity<SuccessResponse<?>> getReportFeedbackApplicationsForAdmin(@PathVariable final Long reportId,
                                                                                    final Pageable pageable) {
        final GetReportFeedbackApplicationsForAdminResponseDto responseDto = getReportFeedbackApplicationsForAdminService.execute(reportId, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[테스트 중] 어드민 - 진단서 참여자 결제 정보 조회", description = "[서류 진단서 참여자 or 첨삭 참여자 -> 결제정보] feedbackApplicationId가 null 여부 = 1:1 첨삭 여부")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportApplicationPaymentForAdminResponseDto.class)))
    @ApiErrorCode({REPORT_APPLICATION_NOT_FOUND})
    @GetMapping("/{reportId}/application/{applicationId}/payment")
    public ResponseEntity<SuccessResponse<?>> getReportApplicationPaymentForAdmin(@PathVariable final Long reportId,
                                                                                  @PathVariable final Long applicationId) {
        final GetReportApplicationPaymentForAdminResponseDto responseDto = getReportApplicationPaymentForAdminService.execute(reportId, applicationId);
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
//
//    @Operation(summary = "[구현중] 나의 1:1 첨삭 목록 보기", description = "[My 진단서 보기 -> 맞춤 첨삭] reportType을 제외할 경우 전체 조회")
//    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetMyReportFeedbackResponseDto.class)))
//    @GetMapping("/my/feedback")
//    public ResponseEntity<SuccessResponse<?>> getMyReportFeedbacks(@CurrentUser final User user,
//                                                                   @RequestParam(required = false) final ReportType reportType,
//                    현                                               final Pageable pageable) {
//        final GetMyReportFeedbackResponseDto responseDto = reportService.getMyReportFeedbacks(user, reportType, pageable);
//        return SuccessResponse.ok(responseDto);
//    }
}
