package org.letscareer.letscareer.domain.user.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UserPwSignUpRequestDto(
        @NotEmpty(message = "이메일을 입력해주세요") String email,
        @NotEmpty(message = "이름을 입력해주세요") String name,
        @NotEmpty(message = "전화번호를 입력해주세요") String phoneNum,
        @NotEmpty(message = "비밀번호를 입력해주세요") String password
) {
}
