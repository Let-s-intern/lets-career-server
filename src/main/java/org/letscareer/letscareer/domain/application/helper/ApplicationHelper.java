package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.application.repository.ApplicationRepository;
import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;
import org.letscareer.letscareer.domain.payment.vo.PaymentProgramVo;
import org.letscareer.letscareer.domain.program.vo.ProgramSimpleVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.APPLICATION_ALREADY_CANCELED;
import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.APPLICATION_NOT_FOUND;
import static org.letscareer.letscareer.global.error.GlobalErrorCode.UNAUTHORIZED;

@RequiredArgsConstructor
@Component
public class ApplicationHelper {
    private final ApplicationRepository applicationRepository;

    public void validateAuthorizedUser(User applicationUser, User currentUser) {
        if (!currentUser.getRole().equals(UserRole.ADMIN) && (!Objects.equals(applicationUser.getId(), currentUser.getId()))) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }
    }

    public List<MyApplicationVo> getMyApplications(Long userId, ApplicationStatus status) {
        return applicationRepository.findMyApplications(userId, status);
    }

    public List<PaymentProgramVo> findPaymentProgramVos(Long userId) {
        return applicationRepository.findPaymentProgramVos(userId);
    }

    public Application findByIdOrThrow(Long applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(APPLICATION_NOT_FOUND));
    }

    public ProgramSimpleVo findVWApplicationProgramIdByIdOrThrow(Long applicationId) {
        return applicationRepository.findVWApplicationProgramIdById(applicationId);
    }

    public void checkAlreadyCanceled(Application application) {
        if(application.getIsCanceled()) throw new ConflictException(APPLICATION_ALREADY_CANCELED);
    }
}
