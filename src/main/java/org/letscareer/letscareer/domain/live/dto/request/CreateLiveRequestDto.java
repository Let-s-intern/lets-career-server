package org.letscareer.letscareer.domain.live.dto.request;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.classification.dto.request.CreateLiveClassificationRequestDto;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.live.type.ProgressType;
import org.letscareer.letscareer.domain.price.dto.request.CreateLivePriceRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public record CreateLiveRequestDto(
        @NotNull
        String title,
        String shortDesc,
        String desc,
        String criticalNotice,
        Integer participationCount,
        @NotNull
        String thumbnail,
        @NotNull
        String mentorName,
        @NotNull
        String mentorImg,
        @NotNull
        String mentorCompany,
        @NotNull
        String mentorJob,
        @NotNull
        String mentorCareer,
        @NotNull
        String mentorIntroduction,
        String job,
        String place,
        @NotNull
        LocalDateTime startDate,
        @NotNull
        LocalDateTime endDate,
        @NotNull
        LocalDateTime beginning,
        @NotNull
        LocalDateTime deadline,
        @NotNull
        Boolean vod,
        @NotNull
        ProgressType progressType,
        @NotNull
        List<CreateLiveClassificationRequestDto> programTypeInfo,
        @NotNull
        CreateLivePriceRequestDto priceInfo,
        @NotNull
        List<CreateProgramFaqRequestDto> faqInfo
) {
}
