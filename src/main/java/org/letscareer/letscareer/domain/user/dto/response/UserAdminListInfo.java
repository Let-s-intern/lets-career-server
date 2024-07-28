package org.letscareer.letscareer.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.user.vo.UserAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record UserAdminListInfo(
        UserAdminVo userInfo,
        List<UserApplicationInfo> applicationInfos
) {
    public static UserAdminListInfo of(UserAdminVo userInfo,
                                       List<UserApplicationInfo> applicationInfos) {
        return UserAdminListInfo.builder()
                .userInfo(userInfo)
                .applicationInfos(applicationInfos)
                .build();
    }
}
