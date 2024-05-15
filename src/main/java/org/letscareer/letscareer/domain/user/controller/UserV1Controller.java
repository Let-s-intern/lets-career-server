package org.letscareer.letscareer.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.dto.request.UserAddInfoRequestDto;
import org.letscareer.letscareer.domain.user.dto.request.UserPwSignInRequestDto;
import org.letscareer.letscareer.domain.user.dto.request.UserPwSignUpRequestDto;
import org.letscareer.letscareer.domain.user.dto.response.UserAdminListResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.service.UserService;
import org.letscareer.letscareer.global.common.annotation.CurrentUser;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
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

    @Operation(summary = "유저 추가정보 입력", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PostMapping("/info")
    public ResponseEntity<SuccessResponse<?>> addUserInfo(@CurrentUser User user,
                                                          @RequestBody final UserAddInfoRequestDto addInfoRequestDto) {
        userService.addUserInfo(user.getId(), addInfoRequestDto);
        return SuccessResponse.ok(null);
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