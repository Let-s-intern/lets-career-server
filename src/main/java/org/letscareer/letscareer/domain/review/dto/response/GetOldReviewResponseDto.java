package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewVo;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record GetOldReviewResponseDto(
        String title,
        String name,
        String content,
        Integer score,
        LocalDateTime createdDate
) {
    public static GetOldReviewResponseDto of(OldReviewVo vo, String title) {
        return GetOldReviewResponseDto.builder()
                .title(title)
                .name(vo.name())
                .content(vo.content())
                .score(vo.score())
                .createdDate(vo.createdDate())
                .build();
    }
}
