package org.letscareer.letscareer.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.dto.request.UserAddInfoRequestDto;
import org.letscareer.letscareer.domain.user.dto.request.UserPwSignInRequestDto;
import org.letscareer.letscareer.domain.user.dto.request.UserPwSignUpRequestDto;
import org.letscareer.letscareer.domain.user.dto.response.UserAdminListResponseDto;
import org.letscareer.letscareer.domain.user.service.UserService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.security.user.PrincipalDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserV1Controller {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<?>> pwSignUp(@RequestBody @Valid UserPwSignUpRequestDto pwSignUpRequestDto) {
        userService.pwSignUp(pwSignUpRequestDto);
        return SuccessResponse.created(null);
    }

    @PostMapping("/signin")
    public ResponseEntity<SuccessResponse<?>> pwSignIn(@RequestBody @Valid UserPwSignInRequestDto pwSignInRequestDto) {
        return SuccessResponse.ok(userService.pwSignIn(pwSignInRequestDto));
    }

    @PostMapping("/info")
    public ResponseEntity<SuccessResponse<?>> addUserInfo(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                          @RequestBody UserAddInfoRequestDto addInfoRequestDto) {
        userService.addUserInfo(principalDetails, addInfoRequestDto);
        return SuccessResponse.ok(null);
    }

    @GetMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> getUsersForAdmin(@PageableDefault Pageable pageable) {
        final UserAdminListResponseDto responseDto = userService.getUsers(pageable);
        return SuccessResponse.ok(responseDto);
    }

}