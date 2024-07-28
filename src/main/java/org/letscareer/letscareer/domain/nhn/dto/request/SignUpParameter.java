package org.letscareer.letscareer.domain.nhn.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.AuthProvider;

import java.time.LocalDate;

@Builder(access = AccessLevel.PRIVATE)
public record SignUpParameter(
    String name,
    String userEmail,
    String loginType,
    LocalDate createDate
) {
    public static SignUpParameter of(User user) {
        return SignUpParameter.builder()
                .name(user.getName())
                .userEmail(user.getEmail())
                .loginType(user.getAuthProvider().getDesc() + " 로그인")
                .createDate(user.getCreateDate().toLocalDate())
                .build();
    }
}
