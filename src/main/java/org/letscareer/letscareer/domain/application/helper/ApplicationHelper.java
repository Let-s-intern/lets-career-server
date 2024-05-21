package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.repository.ApplicationRepository;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.global.error.GlobalErrorCode.UNAUTHORIZED;

@RequiredArgsConstructor
@Component
public class ApplicationHelper {
    private final ApplicationRepository applicationRepository;

    public void validateAuthorizedUser(User applicationUser, User currentUser) {
        if(!currentUser.getRole().equals(UserRole.ADMIN) && (!Objects.equals(applicationUser.getId(), currentUser.getId()))) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }
    }

    public List<MyApplicationVo> getMyApplications(Long userId) {
        return applicationRepository.findMyApplications(userId);
    }
}
