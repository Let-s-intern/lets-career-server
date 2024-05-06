package org.letscareer.letscareer.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.dto.request.UserPwSignUpRequestDto;
import org.letscareer.letscareer.domain.user.service.UserService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
