package org.letscareer.letscareer.domain.user.dto.request;

import org.letscareer.letscareer.domain.user.type.AccountType;
import org.letscareer.letscareer.domain.user.type.UserGrade;

public record UserAddInfoRequestDto(
        String university,
        UserGrade grade,
        String major,
        String wishJob,
        String wishCompany,
        Boolean marketingAgree,
        String contactEmail,
        AccountType accountType,
        String accountNum,
        String accountOwner
) {
}
