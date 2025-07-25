package org.letscareer.letscareer.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserBlogReviewRequestDto(
        @NotNull Long missionId,
        @NotBlank String url,
        String bankName,
        String accountNumber
) {
}