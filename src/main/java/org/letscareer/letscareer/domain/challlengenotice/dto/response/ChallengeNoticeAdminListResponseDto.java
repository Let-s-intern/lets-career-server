package org.letscareer.letscareer.domain.challlengenotice.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeNoticeAdminListResponseDto(
        List<ChallengeNoticeAdminVo> challengeNoticeAdminList
) {
    public static ChallengeNoticeAdminListResponseDto of(List<ChallengeNoticeAdminVo> challengeNoticeAdminList) {
        return ChallengeNoticeAdminListResponseDto.builder()
                .challengeNoticeAdminList(challengeNoticeAdminList)
                .build();
    }
}
