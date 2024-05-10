package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.classification.vo.LiveClassificationVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.live.vo.LiveDetailVo;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveDetailResponseDto(
        LiveDetailVo liveInfo,
        List<LiveClassificationVo> classificationInfo,
        LivePriceDetailVo priceInfo,
        List<FaqDetailVo> faqInfo
) {
    public static GetLiveDetailResponseDto of(LiveDetailVo liveInfo,
                                              List<LiveClassificationVo> classificationInfo,
                                              LivePriceDetailVo priceInfo,
                                              List<FaqDetailVo> faqInfo) {
        return GetLiveDetailResponseDto.builder()
                .liveInfo(liveInfo)
                .classificationInfo(classificationInfo)
                .priceInfo(priceInfo)
                .faqInfo(faqInfo)
                .build();
    }
}
