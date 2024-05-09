package org.letscareer.letscareer.domain.application.vo;

import java.time.LocalDateTime;

public record AdminLiveApplicationVo(
        String name,
        String email,
        String phoneNum,
        String couponName,
        Integer totalCost,
        Boolean isConfirmed,
        LocalDateTime created_date
) {
}
