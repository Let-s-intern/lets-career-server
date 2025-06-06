package org.letscareer.letscareer.domain.live.dto.request;

import org.letscareer.letscareer.domain.admincalssification.request.CreateLiveAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.dto.request.CreateLiveClassificationRequestDto;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.live.type.ProgressType;
import org.letscareer.letscareer.domain.price.dto.request.CreateLivePriceRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateLiveRequestDto(
        String title,
        String shortDesc,
        String desc,
        String criticalNotice,
        Integer participationCount,
        String thumbnail,
        String desktopThumbnail,
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
        ProgressType progressType,
        Boolean vod,
        Boolean isVisible,
        List<CreateLiveClassificationRequestDto> programTypeInfo,
        List<CreateLiveAdminClassificationRequestDto> adminProgramTypeInfo,
        CreateLivePriceRequestDto priceInfo,
        List<CreateProgramFaqRequestDto> faqInfo
) {
}
