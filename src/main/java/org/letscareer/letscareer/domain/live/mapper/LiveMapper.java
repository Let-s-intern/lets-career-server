package org.letscareer.letscareer.domain.live.mapper;

import org.letscareer.letscareer.domain.classification.vo.LiveClassificationVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.live.dto.response.GetLiveDetailResponseDto;
import org.letscareer.letscareer.domain.live.vo.LiveDetailVo;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LiveMapper {
    public GetLiveDetailResponseDto createLiveDetailResponseDto(LiveDetailVo liveInfo,
                                                                List<LiveClassificationVo> classificationInfo,
                                                                LivePriceDetailVo priceInfo,
                                                                List<FaqDetailVo> faqInfo) {
        return GetLiveDetailResponseDto.of(liveInfo, classificationInfo, priceInfo, faqInfo);
    }

}
