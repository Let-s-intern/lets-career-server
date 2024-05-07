package org.letscareer.letscareer.domain.live.dto.request;

import org.letscareer.letscareer.domain.classification.dto.request.CreateChallengeClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.dto.request.CreateLiveClassificationRequestDto;
import org.letscareer.letscareer.domain.faq.dto.request.CreateFaqRequestDto;
import org.letscareer.letscareer.domain.live.type.ProgressType;
import org.letscareer.letscareer.domain.price.dto.request.CreateLivePriceRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public record CreateLiveRequestDto(
        String title,
        String shortDesc,
        String desc,
        Integer participationCount,
        String thumbnail,
        String mentorName,
        String stringJob,
        String place,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline,
        ProgressType progressType,
        List<CreateLiveClassificationRequestDto> programTypeInfo,
        CreateLivePriceRequestDto priceInfo,
        List<CreateFaqRequestDto> faqInfo
) {
}
