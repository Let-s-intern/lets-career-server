package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.classification.vo.LiveClassificationVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.live.type.ProgressType;
import org.letscareer.letscareer.domain.live.vo.LiveDetailVo;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;

import java.time.LocalDateTime;
import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveDetailResponseDto(
        String title,
        String shortDesc,
        String desc,
        Integer participationCount,
        String thumbnail,
        String mentorName,
        String job,
        String place,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime beginning,
        LocalDateTime deadline,
        ProgressType progressType,
        List<LiveClassificationVo> classificationInfo,
        LivePriceDetailVo priceInfo,
        List<FaqDetailVo> faqInfo
) {
    public static GetLiveDetailResponseDto of(LiveDetailVo liveInfo,
                                              List<LiveClassificationVo> classificationInfo,
                                              LivePriceDetailVo priceInfo,
                                              List<FaqDetailVo> faqInfo) {
        return GetLiveDetailResponseDto.builder()
                .title(liveInfo.title())
                .shortDesc(liveInfo.shortDesc())
                .desc(liveInfo.desc())
                .participationCount(liveInfo.participationCount())
                .thumbnail(liveInfo.thumbnail())
                .startDate(liveInfo.startDate())
                .endDate(liveInfo.endDate())
                .beginning(liveInfo.beginning())
                .deadline(liveInfo.deadline())
                .progressType(liveInfo.progressType())
                .classificationInfo(classificationInfo)
                .priceInfo(priceInfo)
                .faqInfo(faqInfo)
                .build();
    }
}
