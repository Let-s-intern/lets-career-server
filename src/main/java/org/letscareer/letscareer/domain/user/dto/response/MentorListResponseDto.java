package org.letscareer.letscareer.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.user.vo.MentorAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record MentorListResponseDto(
        List<MentorAdminVo> mentorList
) {
    public static MentorListResponseDto of(List<MentorAdminVo> mentorList) {
        return MentorListResponseDto.builder()
                .mentorList(mentorList)
                .build();
    }
}
