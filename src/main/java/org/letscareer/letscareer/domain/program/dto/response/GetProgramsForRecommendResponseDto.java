package org.letscareer.letscareer.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeRecommendVo;
import org.letscareer.letscareer.domain.live.vo.LiveRecommendVo;
import org.letscareer.letscareer.domain.report.vo.ReportRecommendVo;
import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetProgramsForRecommendResponseDto(
        List<ChallengeRecommendVo> challengeList,
        LiveRecommendVo live,
        List<VodDetailVo> vodList,
        List<ReportRecommendVo> reportList
) {
    public static GetProgramsForRecommendResponseDto of(List<ChallengeRecommendVo> challengeList,
                                                        LiveRecommendVo live,
                                                        List<VodDetailVo> vodList,
                                                        List<ReportRecommendVo> reportList) {
        return GetProgramsForRecommendResponseDto.builder()
                .challengeList(challengeList)
                .live(live)
                .vodList(vodList)
                .reportList(reportList)
                .build();
    }
}
