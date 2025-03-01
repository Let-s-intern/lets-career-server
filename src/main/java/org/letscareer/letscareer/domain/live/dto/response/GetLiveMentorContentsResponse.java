package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.live.vo.LiveMentorVo;
import org.letscareer.letscareer.domain.review.dto.response.GetLiveMentorReviewResponseDto;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveMentorContentsResponse(
        LiveMentorVo liveMentorVo,
        List<String> questionList,
        List<String> motivateList,
        List<GetLiveMentorReviewResponseDto> reviewList
) {

    public static GetLiveMentorContentsResponse of(LiveMentorVo liveMentorVo, List<String> questionList, List<String> motivateList, List<GetLiveMentorReviewResponseDto> reviewList) {
        return GetLiveMentorContentsResponse.builder()
                .liveMentorVo(liveMentorVo)
                .questionList(questionList)
                .motivateList(motivateList)
                .reviewList(reviewList)
                .build();
    }
}
