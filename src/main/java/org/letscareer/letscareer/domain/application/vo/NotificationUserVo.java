package org.letscareer.letscareer.domain.application.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.user.entity.User;

@Builder
public record NotificationUserVo(
        User user,
        Long applicationId
) {
}
