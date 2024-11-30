package org.letscareer.letscareer.domain.review.mapper;

import org.letscareer.letscareer.domain.review.dto.response.GetReviewDetailListResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewDetailResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.letscareer.letscareer.domain.review.vo.ReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMapper {
    public GetReviewDetailResponseDto toGetReviewDetailResponseDto(ReviewDetailVo reviewDetailVo) {
        return GetReviewDetailResponseDto.of(reviewDetailVo);
    }

    public GetReviewResponseDto toGetReviewResponseDto(ReviewVo vo, String title) {
        return GetReviewResponseDto.of(vo, title);
    }

    public GetReviewDetailListResponseDto toGetReviewDetailListResponseDto(List<ReviewDetailVo> reviewDetailVos) {
        return GetReviewDetailListResponseDto.of(reviewDetailVos);
    }
}
