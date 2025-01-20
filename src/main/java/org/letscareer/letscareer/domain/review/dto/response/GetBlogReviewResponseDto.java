package org.letscareer.letscareer.domain.review.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.review.vo.BlogReviewVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetBlogReviewResponseDto(
        List<BlogReviewVo> reviewList,
        PageInfo pageInfo
) {
    public static GetBlogReviewResponseDto of(List<BlogReviewVo> reviewList, PageInfo pageInfo) {
        return GetBlogReviewResponseDto.builder()
                .reviewList(reviewList)
                .pageInfo(pageInfo)
                .build();
    }
}
