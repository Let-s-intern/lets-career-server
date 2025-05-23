package org.letscareer.letscareer.domain.application.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.price.type.ChallengePricePlanType;
import org.letscareer.letscareer.domain.user.type.AccountType;
import org.letscareer.letscareer.domain.user.type.UserGrade;

import java.time.LocalDateTime;

@Builder
public record AdminChallengeApplicationVo(
        Long id,
        Long paymentId,
        String name,
        String email,
        String phoneNum,
        String university,
        UserGrade grade,
        String major,
        String couponName,
        Integer couponDiscount,
        Integer finalPrice,
        Integer programPrice,
        Integer programDiscount,
        Integer refundPrice,
        String orderId,
        Boolean isCanceled,
        String wishJob,
        String wishCompany,
        String inflowPath,
        LocalDateTime createDate,
        AccountType accountType,
        String accountNum,
        ChallengePricePlanType challengePricePlanType
) {
}
