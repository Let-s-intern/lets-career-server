package org.letscareer.letscareer.domain.live.dto.request;

import org.letscareer.letscareer.domain.classification.dto.request.CreateLiveClassificationRequestDto;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.live.type.ProgressType;
import org.letscareer.letscareer.domain.price.dto.request.CreateLivePriceRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public record CreateLiveRequestDto(
        String title,
        String shortDesc,
        String desc,
        String criticalNotice,
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
        List<CreateLiveClassificationRequestDto> programTypeInfo,
        CreateLivePriceRequestDto priceInfo,
        List<CreateProgramFaqRequestDto> faqInfo
) {
}
