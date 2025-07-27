package org.letscareer.letscareer.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.user.type.AccountType;

public record CreateUserBlogReviewRequestDto(
        @NotNull Long missionId,
        @NotBlank String url,
        AccountType accountType,
        String accountNum
) {
}