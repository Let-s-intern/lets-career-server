package org.letscareer.letscareer.domain.user.dto.request;

import org.letscareer.letscareer.domain.user.type.UserGrade;
import org.letscareer.letscareer.domain.user.type.UserRole;

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
        String wishCompany,
        UserRole role,
        Boolean isMentor
) {
}
