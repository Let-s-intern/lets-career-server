package org.letscareer.letscareer.domain.application.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.user.type.UserGrade;

import java.time.LocalDateTime;

@Builder
public record AdminChallengeApplicationVo(
        Long paymentId,
        String name,
        String email,
        String phoneNum,
        String university,
        UserGrade grade,
        String major,
        String couponName,
        Integer totalCost,
        Boolean isConfirmed,
        String wishJob,
        String wishCompany,
        String inflowPath,
        LocalDateTime createDate
) {
}
