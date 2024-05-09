package org.letscareer.letscareer.domain.user.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UserPwSignInRequestDto(
        @NotEmpty(message = "이메일을 입력해주세요") String email,
        @NotEmpty(message = "비밀번호를 입력해주세요")String password
) {
}
