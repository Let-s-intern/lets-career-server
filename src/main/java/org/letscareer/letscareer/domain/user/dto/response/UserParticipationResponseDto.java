package org.letscareer.letscareer.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.user.entity.User;

@Builder(access = AccessLevel.PRIVATE)
public record UserParticipationResponseDto(
        String name,
        String phoneNumber,
        String email,
        String contactEmail
) {
    public static UserParticipationResponseDto of(User user) {
        return UserParticipationResponseDto.builder()
                .name(user.getName())
                .phoneNumber(user.getPhoneNum())
                .email(user.getEmail())
                .contactEmail(user.getContactEmail())
                .build();
    }
}
