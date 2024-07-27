package org.letscareer.letscareer.domain.application.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.user.type.AccountType;

@Builder
public record UserChallengeApplicationVo(
        Long id,
        String name,
        String contactEmail,
        String phoneNum,
        AccountType accountType,
        String accountNum
) {
}
