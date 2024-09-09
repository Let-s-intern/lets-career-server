package org.letscareer.letscareer.domain.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.req.*;
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

import static org.letscareer.letscareer.global.common.entity.SwaggerEnum.*;

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
    private final GetReportThumbnailService getReportThumbnailService;
    private final GetReportPaymentService getReportPaymentService;
    private final CreateReportService createReportService;
    private final CreateReportApplicationService createReportApplicationService;
    private final UpdateReportService updateReportService;
    private final UpdateReportFeedbackSchedule updateReportFeedbackSchedule;
    private final UpdateReportDocumentService updateReportDocumentService;
    private final UpdateReportApplicationStatusService updateReportApplicationStatusService;
    private final DeleteReportService deleteReportService;
    private final CancelReportApplicationService cancelReportApplicationService;

    @Operation(summary = "어드민 - 진단서 목록 조회")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportsForAdminResponseDto.class)))
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getReportsForAdmin(final Pageable pageable) {
        final GetReportsForAdminResponseDto responseDto = getReportsForAdminService.execute(pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "어드민 - 진단서 상세 조회")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportDetailForAdminResponseDto.class)))
    @ApiErrorCode({REPORT_NOT_FOUND})
    @GetMapping("/{reportId}/admin")
    public ResponseEntity<SuccessResponse<?>> getReportDetailForAdmin(@PathVariable final Long reportId) {
        final GetReportDetailForAdminResponseDto responseDto = getReportDetailForAdminService.execute(reportId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "어드민 - 진단서 참여자 목록 조회")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportApplicationsForAdminResponseDto.class)))
    @GetMapping("/applications")
    public ResponseEntity<SuccessResponse<?>> getReportApplicationsForAdmin(@RequestParam(required = false) final Long reportId,
                                                                            @RequestParam(required = false) final ReportType reportType,
                                                                            @RequestParam(required = false) final ReportPriceType priceType,
                                                                            @RequestParam(required = false) final Boolean isApplyFeedback,
                                                                            final Pageable pageable) {
        final GetReportApplicationsForAdminResponseDto responseDto = getReportApplicationsForAdminService.execute(reportId, reportType, priceType, isApplyFeedback, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "어드민 - 진단서 참여자 옵션")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportApplicationOptionsForAdminResponseDto.class)))
    @ApiErrorCode({REPORT_APPLICATION_NOT_FOUND})
    @GetMapping("/application/options")
    public ResponseEntity<SuccessResponse<?>> getReportApplicationPaymentForAdmin(@RequestParam(required = false) final Long reportId,
                                                                                  @RequestParam(required = false) final Long applicationId,
                                                                                  @RequestParam(required = false) final ReportType reportType,
                                                                                  @RequestParam(required = false) final ReportPriceType priceType,
                                                                                  @RequestParam(required = false) final String code) {
        final GetReportApplicationOptionsForAdminResponseDto responseDto = getReportApplicationPaymentForAdminService.execute(reportId, applicationId, reportType, priceType, code);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "유저 - 진단서 상세 조회", description = "[서류 진단 신청하기]")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportDetailResponseDto.class)))
    @ApiErrorCode({REPORT_NOT_FOUND})
    @GetMapping("/{reportId}")
    public ResponseEntity<SuccessResponse<?>> getReportDetail(@PathVariable final Long reportId) {
        final GetReportDetailResponseDto responseDto = getReportDetailService.execute(reportId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "유저 - 홈화면 조회", description = "[홈화면]")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportThumbnailResponseDto.class)))
    @ApiErrorCode({REPORT_NOT_FOUND})
    @GetMapping("/active")
    public ResponseEntity<SuccessResponse<?>> getReportThumbnail() {
        final GetReportThumbnailResponseDto responseDto = getReportThumbnailService.execute();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "유저 - 진단서 가격 상세 정보", description = "[서류 진단 신청하기 -> 하단 모달]")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportPriceDetailResponseDto.class)))
    @ApiErrorCode({REPORT_NOT_FOUND})
    @GetMapping("/{reportId}/price")
    public ResponseEntity<SuccessResponse<?>> getReportPriceDetail(@PathVariable final Long reportId) {
        final GetReportPriceDetailResponseDto responseDto = getReportPriceDetailService.execute(reportId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "유저 - 나의 진단서 목록", description = "[My 진단서 보기 -> 서류 진단서] reportType을 제외할 경우 전체 조회")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetMyReportResponseDto.class)))
    @GetMapping("/my")
    public ResponseEntity<SuccessResponse<?>> getMyReports(@CurrentUser final User user,
                                                           @RequestParam(required = false) final ReportType reportType,
                                                           final Pageable pageable) {
        final GetMyReportResponseDto responseDto = getMyReportService.execute(user, reportType, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(
            summary = "진단서 결제 내역 상세 조회",
            description = """
                    finalPrice : 총 결제금액 or 환불금액 <br>
                    programPrice : 원가 금액 [피그마 용어 - 결제상품] <br>
                    programDiscount : 원가 할인 금액 [피그마 용어 - 할인] <br>
                    reportRefundPrice : 진단서 환불 금액 <br>
                    feedbackRefundPrice : 1:1 첨삭 환불 금액 <br>
                    reportPriceInfo : 진단서 가격 정보 <br>
                    reportOptionInfos : 진단서 옵션 가격 정보 <br>
                    feedbackPriceInfo : 1대1 첨삭 가격 정보 <br>
                    """
    )
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetReportPaymentResponseDto.class)))
    @GetMapping("/application/{applicationId}/payment")
    public ResponseEntity<SuccessResponse<?>> getReportPayment(@CurrentUser final User user,
                                                               @PathVariable final Long applicationId) {
        final GetReportPaymentResponseDto responseDto = getReportPaymentService.execute(user, applicationId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "진단서 프로그램 생성")
    @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createReport(@RequestBody final CreateReportRequestDto requestDto) {
        createReportService.execute(requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(
            summary = "[테스트 중] 진단서 신청",
            description = """
                    amount : 실제 결제 금액 <br>
                    programPrice : (서류 진단 + 옵션 + 1대1 첨삭) 정가 <br>
                    programDiscount : (서류 진단 + 옵션 + 1대1 첨삭) 할인 가격, 쿠폰 할인 제외 <br>
                    """
    )
    @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    @PostMapping("/{reportId}/application")
    public ResponseEntity<SuccessResponse<?>> createReportApplication(@CurrentUser final User user,
                                                                      @PathVariable final Long reportId,
                                                                      @RequestBody final CreateReportApplicationRequestDto requestDto) {
        CreateReportApplicationResponseDto responseDto = createReportApplicationService.execute(user, reportId, requestDto);
        return SuccessResponse.created(responseDto);
    }

    @Operation(summary = "진단서 프로그램 수정")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @ApiErrorCode({REPORT_CONFLICT_VISIBLE_DATE})
    @PatchMapping("/{reportId}")
    public ResponseEntity<SuccessResponse<?>> updateReport(@PathVariable final Long reportId,
                                                           @RequestBody final UpdateReportRequestDto requestDto) {
        updateReportService.execute(reportId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "진단서 업로드")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @PatchMapping("/application/{applicationId}/document")
    public ResponseEntity<SuccessResponse<?>> updateReportDocument(@PathVariable final Long applicationId,
                                                                   @RequestBody final UpdateReportDocumentRequestDto requestDto) {
        updateReportDocumentService.execute(applicationId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(
            summary = "진단서 상태 변경",
            description = """
                    [NotNull] reportApplicationStatus <br>
                    APPLIED(1, "신청완료") <br>
                    REPORTING(2, "진단중") <br>
                    REPORTED(3, "진단서 업로드") <br>
                    COMPLETED(4, "진단완료") <br>
                    """
    )
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @PatchMapping("/application/{applicationId}/status")
    public ResponseEntity<SuccessResponse<?>> updateReportStatus(@PathVariable final Long applicationId,
                                                                 @RequestBody final UpdateReportApplicationStatusRequestDto requestDto) {
        updateReportApplicationStatusService.execute(applicationId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(
            summary = "1대1 첨삭 일정 관리",
            description = """
                    [NotNull] reportFeedbackStatus <br>
                    APPLIED(1, "신청완료") <br>
                    PENDING(2, "일정 선택완료") <br>
                    CONFIRMED(3, "일정확정") <br>
                    COMPLETED(4, "진행완료") <br>
                    """
    )
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @PatchMapping("/{reportId}/application/{applicationId}/schedule")
    public ResponseEntity<SuccessResponse<?>> updateReportFeedbackSchedule(@PathVariable final Long reportId,
                                                                           @PathVariable final Long applicationId,
                                                                           @RequestBody @Valid final UpdateFeedbackScheduleRequestDto requestDto) {
        updateReportFeedbackSchedule.execute(reportId, applicationId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "진단서 프로그램 삭제")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @DeleteMapping("/{reportId}")
    public ResponseEntity<SuccessResponse<?>> deleteReport(@PathVariable final Long reportId) {
        deleteReportService.execute(reportId);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "[테스트 중] 진단서 신청 취소")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    @DeleteMapping("/application/{reportApplicationId}")
    public ResponseEntity<SuccessResponse<?>> cancelReportApplication(@CurrentUser final User user,
                                                                      @PathVariable final Long reportApplicationId) {
        cancelReportApplicationService.execute(user, reportApplicationId);
        return ResponseEntity.ok(null);
    }
}
