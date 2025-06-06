package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.admincalssification.vo.ChallengeAdminClassificationDetailVo;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeDetailVo;
import org.letscareer.letscareer.domain.classification.vo.ChallengeClassificationDetailVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;

import java.time.LocalDateTime;
import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeDetailResponseDto(
        String title,
        String shortDesc,
        String desc,
        String criticalNotice,
        Integer participationCount,
        String thumbnail,
        String desktopThumbnail,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime beginning,
        LocalDateTime deadline,
        String chatLink,
        String chatPassword,
        ChallengeType challengeType,
        List<ChallengeClassificationDetailVo> classificationInfo,
        List<ChallengeAdminClassificationDetailVo> adminClassificationInfo,
        List<ChallengePriceDetailVo> priceInfo,
        List<FaqDetailVo> faqInfo
) {
    public static GetChallengeDetailResponseDto of(ChallengeDetailVo challengeInfo,
                                                   List<ChallengeClassificationDetailVo> classificationInfo,
                                                   List<ChallengeAdminClassificationDetailVo> adminClassificationInfo,
                                                   List<ChallengePriceDetailVo> priceInfo,
                                                   List<FaqDetailVo> faqInfo) {
        return GetChallengeDetailResponseDto.builder()
                .title(challengeInfo.title())
                .shortDesc(challengeInfo.shortDesc())
                .desc(challengeInfo.desc())
                .criticalNotice(challengeInfo.criticalNotice())
                .participationCount(challengeInfo.participationCount())
                .thumbnail(challengeInfo.thumbnail())
                .desktopThumbnail(challengeInfo.desktopThumbnail())
                .startDate(challengeInfo.startDate())
                .endDate(challengeInfo.endDate())
                .beginning(challengeInfo.beginning())
                .deadline(challengeInfo.deadline())
                .chatLink(challengeInfo.chatLink())
                .chatPassword(challengeInfo.chatPassword())
                .challengeType(challengeInfo.challengeType())
                .classificationInfo(classificationInfo)
                .adminClassificationInfo(adminClassificationInfo)
                .priceInfo(priceInfo)
                .faqInfo(faqInfo)
                .build();
    }
}
