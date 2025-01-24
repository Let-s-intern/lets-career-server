package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.admincalssification.vo.LiveAdminClassificationDetailVo;
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
        String criticalNotice,
        Integer participationCount,
        String thumbnail,
        String mentorName,
        String mentorImg,
        String mentorCompany,
        String mentorJob,
        String mentorCareer,
        String mentorIntroduction,
        String job,
        String place,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime beginning,
        LocalDateTime deadline,
        Boolean vod,
        ProgressType progressType,
        List<LiveClassificationVo> classificationInfo,
        List<LiveAdminClassificationDetailVo> adminClassificationInfo,
        LivePriceDetailVo priceInfo,
        List<FaqDetailVo> faqInfo
) {
    public static GetLiveDetailResponseDto of(LiveDetailVo liveInfo,
                                              List<LiveClassificationVo> classificationInfo,
                                              List<LiveAdminClassificationDetailVo> adminClassificationInfo,
                                              LivePriceDetailVo priceInfo,
                                              List<FaqDetailVo> faqInfo) {
        return GetLiveDetailResponseDto.builder()
                .title(liveInfo.title())
                .shortDesc(liveInfo.shortDesc())
                .desc(liveInfo.desc())
                .criticalNotice(liveInfo.criticalNotice())
                .participationCount(liveInfo.participationCount())
                .thumbnail(liveInfo.thumbnail())
                .mentorName(liveInfo.mentorName())
                .mentorImg(liveInfo.mentorImg())
                .mentorCompany(liveInfo.mentorCompany())
                .mentorJob(liveInfo.mentorJob())
                .mentorCareer(liveInfo.mentorCareer())
                .mentorIntroduction(liveInfo.mentorIntroduction())
                .job(liveInfo.job())
                .place(liveInfo.place())
                .startDate(liveInfo.startDate())
                .endDate(liveInfo.endDate())
                .beginning(liveInfo.beginning())
                .deadline(liveInfo.deadline())
                .vod(liveInfo.vod())
                .progressType(liveInfo.progressType())
                .classificationInfo(classificationInfo)
                .adminClassificationInfo(adminClassificationInfo)
                .priceInfo(priceInfo)
                .faqInfo(faqInfo)
                .build();
    }
}
