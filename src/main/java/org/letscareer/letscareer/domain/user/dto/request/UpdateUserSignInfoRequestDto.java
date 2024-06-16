package org.letscareer.letscareer.domain.user.dto.request;

import org.letscareer.letscareer.domain.user.type.UserGrade;

public record UpdateUserSignInfoRequestDto(
        String university,
        UserGrade grade,
        String major,
        String wishJob,
        String wishCompany
) {
}
