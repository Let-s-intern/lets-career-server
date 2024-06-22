package org.letscareer.letscareer.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.AccountType;
import org.letscareer.letscareer.domain.user.type.AuthProvider;
import org.letscareer.letscareer.domain.user.type.UserGrade;

@Builder(access = AccessLevel.PRIVATE)
public record UserInfoResponseDto(
        String name,
        String email,
        String contactEmail,
        String phoneNum,
        String university,
        UserGrade grade,
        String major,
        String wishJob,
        String wishCompany,
        AccountType accountType,
        String accountNum,
        Boolean marketingAgree,
        AuthProvider authProvider
) {
    public static UserInfoResponseDto of(User user) {
        return UserInfoResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .contactEmail(user.getContactEmail())
                .phoneNum(user.getPhoneNum())
                .university(user.getUniversity())
                .grade(user.getGrade())
                .major(user.getMajor())
                .wishJob(user.getWishJob())
                .wishCompany(user.getWishCompany())
                .accountType(user.getAccountType())
                .accountNum(user.getAccountNum())
                .marketingAgree(user.getMarketingAgree())
                .authProvider(user.getAuthProvider())
                .build();
    }
}
