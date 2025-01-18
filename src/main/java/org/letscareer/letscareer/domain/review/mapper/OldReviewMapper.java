package org.letscareer.letscareer.domain.review.mapper;

import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewDetailListResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewDetailResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewResponseDto;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OldReviewMapper {
    public GetOldReviewDetailResponseDto toGetReviewDetailResponseDto(ReviewDetailVo reviewDetailVo) {
        return GetOldReviewDetailResponseDto.of(reviewDetailVo);
    }

    public GetOldReviewResponseDto toGetReviewResponseDto(ReviewVo vo, String title) {
        return GetOldReviewResponseDto.of(vo, title);
    }

    public GetOldReviewDetailListResponseDto toGetReviewDetailListResponseDto(List<ReviewAdminVo> reviewAdminVos) {
        return GetOldReviewDetailListResponseDto.of(reviewAdminVos);
    }
}
