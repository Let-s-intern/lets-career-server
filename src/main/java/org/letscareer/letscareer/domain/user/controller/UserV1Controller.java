package org.letscareer.letscareer.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.dto.request.*;
import org.letscareer.letscareer.domain.user.dto.response.UserAdminListResponseDto;
import org.letscareer.letscareer.domain.user.dto.response.UserInfoResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
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

    @Operation(summary = "유저 이메일 회원가입", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<?>> pwSignUp(@RequestBody @Valid final UserPwSignUpRequestDto pwSignUpRequestDto) {
        userService.pwSignUp(pwSignUpRequestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "유저 이메일 로그인", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PostMapping("/signin")
    public ResponseEntity<SuccessResponse<?>> pwSignIn(@RequestBody @Valid final UserPwSignInRequestDto pwSignInRequestDto) {
        return SuccessResponse.ok(userService.pwSignIn(pwSignInRequestDto));
    }

    @Operation(summary = "유저 정보 업데이트", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping()
    public ResponseEntity<SuccessResponse<?>> updateUser(@CurrentUser User user,
                                                         @RequestBody final UserUpdateRequestDto userUpdateRequestDto) {
        userService.updateUser(user.getId(), userUpdateRequestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "유저 마이페이지 정보", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserInfoResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getUserInfo(@CurrentUser User user) {
        return SuccessResponse.ok(userService.getUserInfo(user));
    }

    @Operation(summary = "비밀번호 변경", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND, SwaggerEnum.MISMATCH_PASSWORD})
    @PatchMapping("/password")
    public ResponseEntity<SuccessResponse<?>> updateUserPassword(@CurrentUser User user,
                                                                 @RequestBody final PasswordUpdateRequestDto passwordUpdateRequestDto) {
        userService.updatePassword(user.getId(), passwordUpdateRequestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "비밀번호 재설정 메일 전송", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @ApiErrorCode({SwaggerEnum.USER_NOT_FOUND})
    @PostMapping("/password")
    public ResponseEntity<SuccessResponse<?>> passwordReset(@RequestBody final PasswordResetRequestDto passwordResetRequestDto) {
        userService.resetPassword(passwordResetRequestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "유저 탈퇴", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping
    public ResponseEntity<SuccessResponse<?>> deleteUser(@CurrentUser User user) {
        userService.deleteUser(user);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "유저 관리자 여부", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    })
    @GetMapping("/is-admin")
    public ResponseEntity<SuccessResponse<?>> isAdmin(@CurrentUser User user) {
        return SuccessResponse.ok(userService.isAdmin(user));
    }

    @Operation(summary = "유저 전체 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserAdminListResponseDto.class)))
    })
    @GetMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> getUsersForAdmin(@PageableDefault Pageable pageable) {
        final UserAdminListResponseDto responseDto = userService.getUsers(pageable);
        return SuccessResponse.ok(responseDto);
    }

}