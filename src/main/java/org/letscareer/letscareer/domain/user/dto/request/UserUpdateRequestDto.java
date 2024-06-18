package org.letscareer.letscareer.domain.user.dto.request;

import jakarta.validation.constraints.NotEmpty;
import org.letscareer.letscareer.domain.user.type.AccountType;
import org.letscareer.letscareer.domain.user.type.UserGrade;

public record UserUpdateRequestDto(
        String email,
        String name,
        String phoneNum,
        String university,
        UserGrade grade,
        String major,
        String wishJob,
        String wishCompany,
        Boolean marketingAgree,
        String contactEmail,
        AccountType accountType,
        String accountNum,
        String accountOwner,
        String inflowPath
) {
}
