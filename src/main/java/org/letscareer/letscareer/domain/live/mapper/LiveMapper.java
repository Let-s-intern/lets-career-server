package org.letscareer.letscareer.domain.live.mapper;

import org.letscareer.letscareer.domain.classification.vo.LiveClassificationVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.live.dto.response.*;
import org.letscareer.letscareer.domain.live.vo.*;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LiveMapper {
    public GetLiveDetailResponseDto toLiveDetailResponseDto(LiveDetailVo liveInfo,
                                                            List<LiveClassificationVo> classificationInfo,
                                                            LivePriceDetailVo priceInfo,
                                                            List<FaqDetailVo> faqInfo) {
        return GetLiveDetailResponseDto.of(liveInfo, classificationInfo, priceInfo, faqInfo);
    }

    public GetLivesResponseDto toGetLivesResponseDto(Page<LiveProfileVo> liveProfileVos) {
        return GetLivesResponseDto.of(liveProfileVos);
    }

    public GetLiveReviewsResponseDto toGetLiveReviewsResponseDto(List<GetReviewResponseDto> reviewResDtoList, PageInfo pageInfo) {
        return GetLiveReviewsResponseDto.of(reviewResDtoList, pageInfo);
    }

    public GetLiveAdminReviewsResponseDto toGetLiveAdminReviewsResponseDto(Page<ReviewAdminVo> reviewVos) {
        return GetLiveAdminReviewsResponseDto.of(reviewVos);
    }

    public GetLiveTitleResponseDto toGetLiveTitleResponseDto(LiveTitleVo titleVo) {
        return GetLiveTitleResponseDto.of(titleVo);
    }

    public GetLiveThumbnailResponseDto toGetLiveThumbnailResponseDto(LiveThumbnailVo thumbnailVo) {
        return GetLiveThumbnailResponseDto.of(thumbnailVo);
    }

    public GetLiveContentResponseDto toGetLiveContentResponseDto(LiveContentVo contentVo) {
        return GetLiveContentResponseDto.of(contentVo);
    }

    public GetLiveApplicationFormResponseDto toGetLiveApplicationFormResponseDto(User user,
                                                                                 Boolean applied,
                                                                                 LiveApplicationFormVo applicationFormVo,
                                                                                 LivePriceDetailVo livePriceDetailVo) {
        return GetLiveApplicationFormResponseDto.of(user, applied, applicationFormVo, livePriceDetailVo);
    }

    public GetLiveMentorContentsResponse toGetLiveMentorContentsResponse(LiveMentorVo liveMentorVo, List<String> questionList, List<String> motivateList, List<String> reviewList) {
        return GetLiveMentorContentsResponse.of(liveMentorVo, questionList, motivateList, reviewList);
    }
}
