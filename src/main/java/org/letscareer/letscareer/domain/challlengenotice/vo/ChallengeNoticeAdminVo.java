package org.letscareer.letscareer.domain.challlengenotice.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.challlengenotice.type.ChallengeNoticeType;

import java.time.LocalDateTime;

@Builder
public record ChallengeNoticeAdminVo(
        Long id,
        ChallengeNoticeType type,
        String title,
        String link,
        LocalDateTime createDate
) {
}
