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
import org.letscareer.letscareer.domain.user.dto.response.*;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.service.UserParticipationService;
import org.letscareer.letscareer.domain.user.service.UserService;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserV1Controller {
    private final UserService userService;
    private final UserParticipationService userParticipationService;


    @Operation(summary = "참여자 정보 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserParticipationResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND})
    @GetMapping("/participation-info")
    public ResponseEntity<SuccessResponse<?>> getUserParticipationInfo(@CurrentUser User user) {
        final UserParticipationResponseDto responseDto = userParticipationService.execute(user);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "유저 마이페이지 정보", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserInfoResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND})
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getUserInfo(@CurrentUser User user) {
        final UserInfoResponseDto responseDto = userService.getUserInfo(user);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[Admin] 유저 세부 정보 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserApplicationResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND})
    @GetMapping("/{userId}")
    public ResponseEntity<SuccessResponse<?>> getUserInfoForAdmin(@PathVariable Long userId) {
        final UserApplicationResponseDto responseDto = userService.getUserInfoForAdmin(userId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "유저 로그아웃", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND})
    @GetMapping("/signout")
    public ResponseEntity<SuccessResponse<?>> signOut(@CurrentUser User user) {
        userService.signOut(user);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "유저 관리자 여부", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND})
    @GetMapping("/isAdmin")
    public ResponseEntity<SuccessResponse<?>> isAdmin(@CurrentUser User user) {
        return SuccessResponse.ok(userService.isAdmin(user));
    }

    @Operation(summary = "[어드민] 유저 전체 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserAdminListResponseDto.class)))
    })
    @GetMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> getUsersForAdmin(@RequestParam(required = false) String email,
                                                               @RequestParam(required = false) String name,
                                                               @RequestParam(required = false) String phoneNum,
                                                               @RequestParam(required = false) String role,
                                                               @PageableDefault Pageable pageable) {
        final UserAdminListResponseDto responseDto = userService.getUsers(email, name, phoneNum, role, pageable);
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

    @Operation(summary = "나의 신청서 전체 조회 - 후기 작성용", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetMyApplicationsResponseDto.class)))
    })
    @GetMapping("/review/applications")
    public ResponseEntity<SuccessResponse<?>> getMyReviewApplications(@CurrentUser User user,
                                                                      @RequestParam(required = false) final ApplicationStatus status) {
        GetMyApplicationsResponseDto responseDto = userService.getMyReviewApplications(user, status);
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


    @Operation(summary = "유저 이메일 회원가입", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.USER_EMAIL_CONFLICT, SwaggerEnum.USER_PHONE_NUMBER_CONFLICT, SwaggerEnum.INVALID_PASSWORD, SwaggerEnum.INVALID_PHONE_NUMBER, SwaggerEnum.INVALID_EMAIL})
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<?>> pwSignUp(@RequestBody @Valid final UserPwSignUpRequestDto pwSignUpRequestDto) {
        userService.pwSignUp(pwSignUpRequestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "유저 이메일 로그인", responses = {
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

    @Operation(summary = "유저 토큰 재발급", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = TokenResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.INVALID_TOKEN, SwaggerEnum.NOT_REFRESH_TOKEN})
    @PatchMapping("/token")
    public ResponseEntity<SuccessResponse<?>> reissueToken(@RequestBody @Valid TokenReissueRequestDto tokenReissueRequestDto) {
        TokenResponseDto responseDto = userService.reissueToken(tokenReissueRequestDto);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "유저 정보 업데이트", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND, SwaggerEnum.USER_PHONE_NUMBER_CONFLICT, SwaggerEnum.INVALID_PHONE_NUMBER, SwaggerEnum.INVALID_EMAIL})
    @PatchMapping
    public ResponseEntity<SuccessResponse<?>> updateUser(@CurrentUser User user,
                                                         @RequestBody final UserUpdateRequestDto requestDto) {
        userService.updateUser(user.getId(), requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[ADMIN] 유저 정보 업데이트", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND, SwaggerEnum.USER_PHONE_NUMBER_CONFLICT, SwaggerEnum.INVALID_PHONE_NUMBER, SwaggerEnum.INVALID_EMAIL})
    @PatchMapping("/{userId}")
    public ResponseEntity<SuccessResponse<?>> updateUserForAdmin(@PathVariable final Long userId,
                                                                 @RequestBody final UpdateUserForAdminRequestDto requestDto) {
        userService.updateUserForAdmin(userId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "회원가입-추가 정보 업데이트", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode(SwaggerEnum.USER_NOT_FOUND)
    @PatchMapping("/additional-info")
    public ResponseEntity<SuccessResponse<?>> updateUserForSign(@RequestBody final UpdateUserSignInfoRequestDto requestDto) {
        userService.updateUserForSign(requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "유저 탈퇴", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode(SwaggerEnum.USER_NOT_FOUND)
    @DeleteMapping
    public ResponseEntity<SuccessResponse<?>> deleteUser(@CurrentUser User user) {
        userService.deleteUser(user);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[ADMIN] 유저 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.IS_NOT_ADMIN, SwaggerEnum.USER_NOT_FOUND})
    @DeleteMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> deleteUserForAdmin(@CurrentUser User user,
                                                                 @RequestParam String number) {
        userService.deleteUserForAdmin(user, number);
        return SuccessResponse.ok(null);
    }

}