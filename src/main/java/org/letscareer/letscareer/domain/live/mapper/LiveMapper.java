package org.letscareer.letscareer.domain.live.mapper;

import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.classification.vo.LiveClassificationVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.live.dto.response.GetLiveDetailResponseDto;
import org.letscareer.letscareer.domain.live.dto.response.GetLivesResponseDto;
import org.letscareer.letscareer.domain.live.vo.LiveDetailVo;
import org.letscareer.letscareer.domain.live.vo.LiveProfileVo;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;
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

}
