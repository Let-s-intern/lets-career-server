package org.letscareer.letscareer.domain.review.mapper;

import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewDetailListResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewDetailResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewResponseDto;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OldReviewMapper {
    public GetOldReviewDetailResponseDto toGetReviewDetailResponseDto(OldReviewDetailVo oldReviewDetailVo) {
        return GetOldReviewDetailResponseDto.of(oldReviewDetailVo);
    }

    public GetOldReviewResponseDto toGetReviewResponseDto(OldReviewVo vo, String title) {
        return GetOldReviewResponseDto.of(vo, title);
    }

    public GetOldReviewDetailListResponseDto toGetReviewDetailListResponseDto(List<OldReviewAdminVo> oldReviewAdminVos) {
        return GetOldReviewDetailListResponseDto.of(oldReviewAdminVos);
    }
}
