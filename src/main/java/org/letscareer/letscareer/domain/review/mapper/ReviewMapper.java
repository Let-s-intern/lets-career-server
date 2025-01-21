package org.letscareer.letscareer.domain.review.mapper;

import org.letscareer.letscareer.domain.review.dto.response.GetBlogReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.letscareer.letscareer.domain.review.vo.*;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMapper {
    public ReviewVo toReviewVo(ReviewInfoVo reviewInfoVo, List<ReviewItemVo> reviewItemVos) {
        return ReviewVo.of(reviewInfoVo, reviewItemVos);
    }

    public <T> ReviewAdminVo toReviewAdminVo(T reviewInfo, List<ReviewItemAdminVo> reviewItemAdminVos) {
        return ReviewAdminVo.of(reviewInfo, reviewItemAdminVos);
    }

    public GetReviewResponseDto toGetReviewResponseDto(List<ReviewVo> reviewVos, PageInfo pageInfo) {
        return GetReviewResponseDto.of(reviewVos, pageInfo);
    }

    public GetReviewForAdminResponseDto toGetReviewForAdminResponseDto(List<ReviewAdminVo> reviewAdminVos) {
        return GetReviewForAdminResponseDto.of(reviewAdminVos);
    }

    public GetBlogReviewForAdminResponseDto toGetBlogReviewForAdminResponseDto(List<BlogReviewAdminVo> blogReviewAdminVos) {
        return GetBlogReviewForAdminResponseDto.of(blogReviewAdminVos);
    }
}
