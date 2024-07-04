package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record GetReviewResponseDto(
        String title,
        String name,
        String content,
        Integer score,
        LocalDateTime createdDate
) {
    public static GetReviewResponseDto of(ReviewVo vo, String title) {
        return GetReviewResponseDto.builder()
                .title(title)
                .name(vo.name())
                .content(vo.content())
                .score(vo.score())
                .createdDate(vo.createdDate())
                .build();
    }
}
