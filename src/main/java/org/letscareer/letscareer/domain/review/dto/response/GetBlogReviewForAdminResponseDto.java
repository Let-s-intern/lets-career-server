package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.BlogReviewAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetBlogReviewForAdminResponseDto(
        List<BlogReviewAdminVo> reviewList
) {
    public static GetBlogReviewForAdminResponseDto of(List<BlogReviewAdminVo> reviewList) {
        return GetBlogReviewForAdminResponseDto.builder()
                .reviewList(reviewList)
                .build();
    }
}
