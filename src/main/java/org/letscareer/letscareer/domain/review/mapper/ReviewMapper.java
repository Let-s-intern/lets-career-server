package org.letscareer.letscareer.domain.review.mapper;

import org.letscareer.letscareer.domain.review.dto.response.GetReviewForAdminResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMapper {
    public GetReviewForAdminResponseDto toGetReviewForAdminResponseDto(List<?> reviewList) {
        return GetReviewForAdminResponseDto.of(reviewList);
    }
}
