package org.letscareer.letscareer.domain.review.mapper;

import org.letscareer.letscareer.domain.review.dto.response.GetReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewItemAdminVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMapper {
    public <T> ReviewAdminVo toReviewAdminVo(T reviewInfo, List<ReviewItemAdminVo> reviewItemAdminVos) {
        return ReviewAdminVo.of(reviewInfo, reviewItemAdminVos);
    }
    public GetReviewForAdminResponseDto toGetReviewForAdminResponseDto(List<ReviewAdminVo> reviewAdminVos) {
        return GetReviewForAdminResponseDto.of(reviewAdminVos);
    }
}
