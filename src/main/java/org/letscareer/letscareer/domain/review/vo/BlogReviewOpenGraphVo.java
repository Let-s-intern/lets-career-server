package org.letscareer.letscareer.domain.review.vo;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record BlogReviewOpenGraphVo(
        String title,
        String description,
        String url,
        String image
) {
    public static BlogReviewOpenGraphVo of(String title,
                                           String description,
                                           String url,
                                           String image) {
        return BlogReviewOpenGraphVo.builder()
                .title(title)
                .description(description)
                .url(url)
                .image(image)
                .build();
    }
}
