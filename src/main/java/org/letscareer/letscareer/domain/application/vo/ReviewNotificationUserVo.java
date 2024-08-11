package org.letscareer.letscareer.domain.application.vo;

import lombok.Builder;

@Builder
public record ReviewNotificationUserVo(
        String name,
        String phoneNum,
        Long applicationId
) {
}
