package org.letscareer.letscareer.domain.application.vo;

import org.letscareer.letscareer.domain.user.type.UserGrade;

import java.time.LocalDateTime;

public record AdminLiveApplicationVo(
        Long paymentId,
        String name,
        String email,
        String phoneNum,
        String university,
        String major,
        UserGrade grade,
        String motivate,
        String question,
        String couponName,
        Integer totalCost,
        Boolean isConfirmed,
        LocalDateTime created_date
) {
}
