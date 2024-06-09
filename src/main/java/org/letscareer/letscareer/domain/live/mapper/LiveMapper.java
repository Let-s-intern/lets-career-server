package org.letscareer.letscareer.domain.live.mapper;

import org.letscareer.letscareer.domain.classification.vo.LiveClassificationVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.live.dto.response.*;
import org.letscareer.letscareer.domain.live.vo.*;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.domain.user.entity.User;
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

    public GetLiveReviewsResponseDto toGetLiveReviewsResponseDto(Page<ReviewVo> reviewVos) {
        return GetLiveReviewsResponseDto.of(reviewVos);
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
}
