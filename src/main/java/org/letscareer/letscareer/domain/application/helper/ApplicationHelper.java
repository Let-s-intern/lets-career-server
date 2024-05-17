package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.letscareer.letscareer.global.error.GlobalErrorCode.UNAUTHORIZED;

@RequiredArgsConstructor
@Transactional
@Component
public class ApplicationHelper {
    public void validateAuthorizedUser(User applicationUser, User currentUser) {
        if(!currentUser.getRole().equals(UserRole.ADMIN) && (!Objects.equals(applicationUser.getId(), currentUser.getId()))) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }
    }
}
