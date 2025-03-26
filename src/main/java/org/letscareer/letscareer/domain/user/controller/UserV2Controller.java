package org.letscareer.letscareer.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.response.GetMyApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.user.dto.request.*;
import org.letscareer.letscareer.domain.user.dto.response.TokenResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserChallengeInfoResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserParticipationResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.service.UserParticipationService;
import org.letscareer.letscareer.domain.user.service.UserService;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/user")
@RequiredArgsConstructor
public class UserV2Controller {
    private final UserService userService;
    private final UserParticipationService userParticipationService;

    @Operation(summary = "나의 신청서 전체 조회 - 후기 작성용", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetMyApplicationsResponseDto.class)))
    })
    @GetMapping("/review/applications")
    public ResponseEntity<SuccessResponse<?>> getMyReviewApplications(@CurrentUser User user,
                                                                      @RequestParam(required = false) final ApplicationStatus status) {
        GetMyApplicationsResponseDto responseDto = userService.getMyReviewApplications(user, status);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "로그아웃", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND})
    @GetMapping("/signout")
    public ResponseEntity<SuccessResponse<?>> signOut(@CurrentUser User user) {
        userService.signOut(user);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "이메일 회원가입", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.USER_EMAIL_CONFLICT, SwaggerEnum.USER_PHONE_NUMBER_CONFLICT, SwaggerEnum.INVALID_PASSWORD, SwaggerEnum.INVALID_PHONE_NUMBER, SwaggerEnum.INVALID_EMAIL})
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<?>> pwSignUp(@RequestBody @Valid final UserPwSignUpRequestDto pwSignUpRequestDto) {
        userService.pwSignUp(pwSignUpRequestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "이메일 로그인", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND, SwaggerEnum.MISMATCH_PASSWORD})
    @PostMapping("/signin")
    public ResponseEntity<SuccessResponse<?>> pwSignIn(@RequestBody @Valid final UserPwSignInRequestDto pwSignInRequestDto) {
        return SuccessResponse.ok(userService.pwSignIn(pwSignInRequestDto));
    }

    @Operation(summary = "비밀번호 재설정 메일 전송", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND, SwaggerEnum.INVALID_AUTH_PROVIDER_KAKAO, SwaggerEnum.INVALID_AUTH_PROVIDER_NAVER})
    @PostMapping("/password")
    public ResponseEntity<SuccessResponse<?>> passwordReset(@RequestBody @Valid final PasswordResetRequestDto passwordResetRequestDto) {
        userService.resetPassword(passwordResetRequestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "비밀번호 변경", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND, SwaggerEnum.MISMATCH_PASSWORD, SwaggerEnum.INVALID_PASSWORD})
    @PatchMapping("/password")
    public ResponseEntity<SuccessResponse<?>> updateUserPassword(@CurrentUser User user,
                                                                 @RequestBody final PasswordUpdateRequestDto passwordUpdateRequestDto) {
        userService.updatePassword(user.getId(), passwordUpdateRequestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "토큰 재발급", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = TokenResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.INVALID_TOKEN, SwaggerEnum.NOT_REFRESH_TOKEN})
    @PatchMapping("/token")
    public ResponseEntity<SuccessResponse<?>> reissueToken(@RequestBody @Valid TokenReissueRequestDto tokenReissueRequestDto) {
        TokenResponseDto responseDto = userService.reissueToken(tokenReissueRequestDto);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "나의 신청서 전체 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetMyApplicationsResponseDto.class)))
    })
    @GetMapping("/applications")
    public ResponseEntity<SuccessResponse<?>> getMyApplications(@CurrentUser User user,
                                                                @RequestParam(required = false) final ApplicationStatus status) {
        GetMyApplicationsResponseDto responseDto = userService.getMyApplications(user, status);
        return SuccessResponse.ok(responseDto);
    }


    @Operation(summary = "참여자 정보 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserParticipationResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND})
    @GetMapping("/participation-info")
    public ResponseEntity<SuccessResponse<?>> getUserParticipationInfo(@CurrentUser User user) {
        final UserParticipationResponseDto responseDto = userParticipationService.execute(user);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "유저 챌린지 필수 정보 입력 확인", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserChallengeInfoResponseDto.class)))
    })
    @GetMapping("/challenge-info")
    public ResponseEntity<SuccessResponse<?>> checkUserChallengeInfo(@CurrentUser User user) {
        UserChallengeInfoResponseDto responseDto = userService.checkUserChallengeInfo(user);
        return SuccessResponse.ok(responseDto);
    }

}
