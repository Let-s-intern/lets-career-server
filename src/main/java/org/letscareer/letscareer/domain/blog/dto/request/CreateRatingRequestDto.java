package org.letscareer.letscareer.domain.blog.dto.request;

public record CreateRatingRequestDto(
        String title,
        Integer score
) {
}
