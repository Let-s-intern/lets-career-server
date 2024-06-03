package org.letscareer.letscareer.domain.review.mapper;

import org.letscareer.letscareer.domain.review.dto.response.GetReviewDetailResponseDto;
import org.letscareer.letscareer.domain.review.vo.ReviewDetailVo;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public GetReviewDetailResponseDto toGetReviewDetailResponseDto(ReviewDetailVo reviewDetailVo) {
        return GetReviewDetailResponseDto.of(reviewDetailVo);
    }
}
