package org.letscareer.letscareer.domain.user.dto.request;

import org.letscareer.letscareer.domain.user.type.AccountType;
import org.letscareer.letscareer.domain.user.type.UserGrade;

public record UserAddInfoRequestDto(
        String university,
        String major,
        UserGrade grade,
        String wishJob,
        String wishCompany,
        Boolean marketingAgree,
        AccountType accountType,
        String accountNum,
        String accountOwner
) {
}
