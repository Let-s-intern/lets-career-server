package org.letscareer.letscareer.domain.user.dto.request;

import org.letscareer.letscareer.domain.user.type.UserGrade;

public record UpdateUserForAdminRequestDto(
        String name,
        String email,
        String contactEmail,
        String phoneNum,
        String university,
        String inflowPath,
        UserGrade grade,
        String major,
        String wishJob,
        String wishCompany
) {
}
