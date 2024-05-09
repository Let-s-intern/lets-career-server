package org.letscareer.letscareer.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record UserAdminListResponseDto(
        List<UserAdminVo> userAdminList,
        PageInfo pageInfo
) {
    public static UserAdminListResponseDto of(Page<UserAdminVo> userAdminList, PageInfo pageInfo) {
        return UserAdminListResponseDto.builder()
                .userAdminList(userAdminList.getContent())
                .pageInfo(pageInfo)
                .build();
    }
}
