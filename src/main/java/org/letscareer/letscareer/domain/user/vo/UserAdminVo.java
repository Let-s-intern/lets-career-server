package org.letscareer.letscareer.domain.user.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.user.type.AccountType;
import org.letscareer.letscareer.domain.user.type.UserRole;

import java.time.LocalDateTime;

@Builder
public record UserAdminVo(
        Long id,
        String name,
        String email,
        String contactEmail,
        String phoneNum,
        LocalDateTime createdDate,
        AccountType accountType,
        String accountNum,
        Boolean marketingAgree,
        UserRole role
) {
}
