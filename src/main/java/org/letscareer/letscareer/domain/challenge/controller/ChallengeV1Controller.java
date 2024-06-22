package org.letscareer.letscareer.domain.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.challenge.dto.request.CreateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.request.UpdateChallengeApplicationPaybackRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.request.UpdateChallengeRequestDto;
import org.letscareer.letscareer.domain.challenge.dto.response.*;
import org.letscareer.letscareer.domain.challenge.service.ChallengeService;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.faq.dto.response.GetFaqResponseDto;
import org.letscareer.letscareer.domain.mission.type.MissionQueryType;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge")
@RestController
public class ChallengeV1Controller {
    private final ChallengeService challengeService;

    @Operation(summary = "챌린지 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getChallengeList(@RequestParam(required = false) final List<ProgramClassification> typeList,
                                                               @RequestParam(required = false) final List<ProgramStatusType> statusList,
                                                               final Pageable pageable) {
        final GetChallengeResponseDto responseDto = challengeService.getChallengeList(typeList, statusList, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeDetailResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.CHALLENGE_NOT_FOUND})
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> getChallengeDetail(@PathVariable("id") final Long challengeId) {
        final GetChallengeDetailResponseDto responseDto = challengeService.getChallengeDetail(challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 title 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeTitleResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.CHALLENGE_NOT_FOUND})
    @GetMapping("/{id}/title")
    public ResponseEntity<SuccessResponse<?>> getChallengeTitle(@PathVariable("id") final Long challengeId) {
        final GetChallengeTitleResponseDto responseDto = challengeService.getChallengeTitle(challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 섬네일 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeThumbnailResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.CHALLENGE_NOT_FOUND})
    @GetMapping("/{id}/thumbnail")
    public ResponseEntity<SuccessResponse<?>> getChallengeThumbnail(@PathVariable("id") final Long challengeId) {
        final GetChallengeThumbnailResponseDto responseDto = challengeService.getChallengeThumbnail(challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 상세정보 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeContentResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.CHALLENGE_NOT_FOUND})
    @GetMapping("/{id}/content")
    public ResponseEntity<SuccessResponse<?>> getChallengeDetailContent(@PathVariable("id") final Long challengeId) {
        final GetChallengeContentResponseDto responseDto = challengeService.getChallengeDetailContent(challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 faqs 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetFaqResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.CHALLENGE_NOT_FOUND})
    @GetMapping("/{id}/faqs")
    public ResponseEntity<SuccessResponse<?>> getChallengeFaqs(@PathVariable("id") final Long challengeId) {
        final GetFaqResponseDto responseDto = challengeService.getChallengeFaqs(challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 리뷰 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeReviewResponseDto.class)))
    })
    @GetMapping("/reviews")
    public ResponseEntity<SuccessResponse<?>> getReviews(final Pageable pageable) {
        final GetChallengeReviewResponseDto responseDto = challengeService.getReviews(pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 신청폼 조회", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "비회원 유저시 유저 정보와 applied null 값 반환",
                    content = @Content(schema = @Schema(implementation = GetChallengeApplicationFormResponseDto.class))
            )
    })
    @ApiErrorCode({SwaggerEnum.CHALLENGE_NOT_FOUND})
    @GetMapping("/{id}/application")
    public ResponseEntity<SuccessResponse<?>> getChallengeApplicationForm(@PathVariable("id") final Long challengeId,
                                                                          @CurrentUser User user) {
        final GetChallengeApplicationFormResponseDto responseDto = challengeService.getChallengeApplicationForm(user, challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 신청 여부 조회")
    @GetMapping("/{id}/history")
    public ResponseEntity<SuccessResponse<?>> getChallengeExistingApplication(@PathVariable("id") final Long challengeId,
                                                                              @CurrentUser User user) {
        GetChallengeExisingApplicationResponseDto responseDto = challengeService.getChallengeExistingApplication(challengeId, user.getId());
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 프로그램 신청자 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeApplicationsResponseDto.class)))
    })
    @GetMapping("/{id}/applications")
    public ResponseEntity<SuccessResponse<?>> getApplications(@PathVariable("id") final Long challengeId,
                                                              @RequestParam(required = false) final Boolean isConfirmed) {
        final GetChallengeApplicationsResponseDto responseDto = challengeService.getApplications(challengeId, isConfirmed);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 신청자 리뷰 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeAdminReviewResponseDto.class)))
    })
    @GetMapping("/{id}/reviews")
    public ResponseEntity<SuccessResponse<?>> getReviewsForAdmin(@PathVariable("id") final Long challengeId,
                                                                 final Pageable pageable) {
        final GetChallengeAdminReviewResponseDto responseDto = challengeService.getReviewsForAdmin(challengeId, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 챌린지 확정 메일 대상 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeApplicationEmailListResponseDto.class)))
    })
    @GetMapping("/{id}/emails")
    public ResponseEntity<SuccessResponse<?>> getApplicationEmailList(@PathVariable("id") final Long challengeId) {
        final GetChallengeApplicationEmailListResponseDto responseDto = challengeService.getApplicationEmails(challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 챌린지 확정 메일 내용", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeEmailContentsResponseDto.class)))
    })
    @GetMapping("/{id}/mail-contents")
    public ResponseEntity<SuccessResponse<?>> getEmailContents(@PathVariable("id") final Long challengeId) {
        final GetChallengeEmailContentsResponseDto responseDto = challengeService.getEmailContents(challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 챌린지 미션 참가자 패이백 목록", responses = {
            @ApiResponse(responseCode = "200", description = "[th:99] 운영진 점수", content = @Content(schema = @Schema(implementation = GetChallengeApplicationsPaybackResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.PAYMENT_NOT_FOUND})
    @GetMapping("/{id}/applications/payback")
    public ResponseEntity<SuccessResponse<?>> getApplicationsScore(@PathVariable(name = "id") final Long challengeId,
                                                                   final Pageable pageable) {
        final GetChallengeApplicationsPaybackResponseDto responseDto = challengeService.getApplicationsScore(challengeId, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 챌린지 미션별 제출 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeMissionAttendancesResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.PAYMENT_NOT_FOUND})
    @GetMapping("/{challengeId}/mission/{missionId}/attendances")
    public ResponseEntity<SuccessResponse<?>> getMissionAttendances(@PathVariable final Long challengeId,
                                                                    @PathVariable final Long missionId) {
        final GetChallengeMissionAttendancesResponseDto responseDto = challengeService.getMissionAttendances(challengeId, missionId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 접근 권한 확인", responses = {
            @ApiResponse(responseCode = "200")
    })
    @GetMapping("/{id}/access")
    public ResponseEntity<SuccessResponse<?>> checkChallengeDashboardAccessibleUser(@PathVariable(name = "id") final Long challengeId,
                                                                                    @CurrentUser User user) {
        GetChallengeAccessResponseDto responseDto = challengeService.checkChallengeDashboardAccessibleUser(challengeId, user.getId());
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 가이드 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeGuidesResponseDto.class)))
    })
    @GetMapping("/{id}/guides")
    public ResponseEntity<SuccessResponse<?>> getGuides(@PathVariable(name = "id") final Long challengeId) {
        final GetChallengeGuidesResponseDto responseDto = challengeService.getGuides(challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 공지사항 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeNoticesResponseDto.class)))
    })
    @GetMapping("/{id}/notices")
    public ResponseEntity<SuccessResponse<?>> getNotices(@PathVariable(name = "id") final Long challengeId,
                                                         final Pageable pageable) {
        final GetChallengeNoticesResponseDto responseDto = challengeService.getNotices(challengeId, pageable);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 대시보드 미션 점수 현황", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeTotalScoreResponseDto.class)))
    })
    @GetMapping("/{id}/score")
    public ResponseEntity<SuccessResponse<?>> getTotalScore(@PathVariable(name = "id") final Long challengeId,
                                                            @CurrentUser User user) {
        final GetChallengeTotalScoreResponseDto responseDto = challengeService.getTotalScore(challengeId, user.getId());
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 대시보드 일정 및 미션 제출 현황", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeScheduleResponseDto.class)))
    })
    @GetMapping("/{id}/schedule")
    public ResponseEntity<SuccessResponse<?>> getSchedule(@PathVariable(name = "id") final Long challengeId,
                                                          @CurrentUser User user) {
        final GetChallengeScheduleResponseDto responseDto = challengeService.getSchedule(challengeId, user.getId());
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 대시보드 데일리 미션", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeDailyMissionResponseDto.class)))
    })
    @GetMapping("/{id}/daily-mission")
    public ResponseEntity<SuccessResponse<?>> getDailyMission(@PathVariable(name = "id") final Long challengeId,
                                                              @CurrentUser User user) {
        final GetChallengeDailyMissionResponseDto responseDto = challengeService.getDailyMission(challengeId, user);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 나의 기록장 데일리 미션", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeMyDailyMissionResponseDto.class)))
    })
    @GetMapping("/{id}/my/daily-mission")
    public ResponseEntity<SuccessResponse<?>> getMyDailyMission(@PathVariable(name = "id") final Long challengeId,
                                                                @CurrentUser User user) {
        final GetChallengeMyDailyMissionResponseDto responseDto = challengeService.getDashboardDailyMission(challengeId, user);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 나의 기록장 미션 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeMyMissionsResponseDto.class)))
    })
    @GetMapping("/{id}/missions")
    public ResponseEntity<SuccessResponse<?>> getMyMissions(@PathVariable(name = "id") final Long challengeId,
                                                            @RequestParam(name = "type") final MissionQueryType queryType,
                                                            @CurrentUser User user) {
        GetChallengeMyMissionsResponseDto responseDto = challengeService.getMyMissions(challengeId, queryType, user);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "챌린지 나의 기록장 미션 상세 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetChallengeMyMissionDetailResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.MISSION_NOT_FOUND})
    @GetMapping("/{challengeId}/missions/{missionId}")
    public ResponseEntity<SuccessResponse<?>> getMyMissionDetail(@PathVariable final Long challengeId,
                                                                 @PathVariable final Long missionId,
                                                                 @CurrentUser User user) {
        GetChallengeMyMissionDetailResponseDto responseDto = challengeService.getMyMissionDetail(challengeId, missionId, user);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 챌린지 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createChallengeProgram(@RequestBody final CreateChallengeRequestDto requestDto) {
        challengeService.createChallenge(requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[어드민] 챌린지 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.CHALLENGE_NOT_FOUND})
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateChallengeProgram(@PathVariable("id") final Long challengeId,
                                                                     @RequestBody final UpdateChallengeRequestDto requestDto) {
        challengeService.updateChallenge(challengeId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[어드민] 챌린지 미션 참가자 패이백 정보 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.PAYMENT_NOT_FOUND, SwaggerEnum.ATTENDANCE_SCORE_NOT_FOUND})
    @PatchMapping("/{challengeId}/application/{applicationId}/payback")
    public ResponseEntity<SuccessResponse<?>> updateApplicationsScore(@PathVariable final Long challengeId,
                                                                      @PathVariable final Long applicationId,
                                                                      @RequestBody final UpdateChallengeApplicationPaybackRequestDto requestDto) {
        challengeService.updateApplicationsScore(challengeId, applicationId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[어드민] 챌린지 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.CHALLENGE_NOT_FOUND})
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> deleteChallengeProgram(@PathVariable("id") final Long challengeId) {
        challengeService.deleteChallenge(challengeId);
        return SuccessResponse.ok(null);
    }
}
