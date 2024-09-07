package org.letscareer.letscareer.domain.application.event;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Builder(access = AccessLevel.PRIVATE)
@Getter
public class ReportApplicationExpireEvent {
    private Long reportApplicationId;

    public static ReportApplicationExpireEvent of(Long reportApplicationId) {
        return ReportApplicationExpireEvent.builder()
                .reportApplicationId(reportApplicationId)
                .build();
    }
}
