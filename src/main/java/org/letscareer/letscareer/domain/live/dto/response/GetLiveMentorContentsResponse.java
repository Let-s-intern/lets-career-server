package org.letscareer.letscareer.domain.live.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.live.vo.LiveMentorVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetLiveMentorContentsResponse(
        LiveMentorVo liveMentorVo,
        List<String> questionList,
        List<String> motivateList,
        List<String> reviewList
) {

    public static GetLiveMentorContentsResponse of(LiveMentorVo liveMentorVo, List<String> questionList, List<String> motivateList, List<String> reviewList) {
        return GetLiveMentorContentsResponse.builder()
                .liveMentorVo(liveMentorVo)
                .questionList(questionList)
                .motivateList(motivateList)
                .reviewList(reviewList)
                .build();
    }
}
