package org.letscareer.letscareer.domain.review.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.user.type.AccountType;

import java.time.LocalDateTime;

@Builder
public record BlogReviewAdminVo(
        Long blogReviewId,
        LocalDateTime postDate,
        ProgramType programType,
        String programTitle,
        String name,
        String phoneNum,
        AccountType accountType,
        String accountNum,
        String title,
        String url,
        String thumbnail,
        Boolean isVisible
) {
}
