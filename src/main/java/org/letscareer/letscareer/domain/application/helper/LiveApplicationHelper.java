package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.application.repository.LiveApplicationRepository;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
import org.letscareer.letscareer.domain.application.vo.ReviewNotificationUserVo;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.*;

@RequiredArgsConstructor
@Component
public class LiveApplicationHelper {
    private final LiveApplicationRepository liveApplicationRepository;

    public List<AdminLiveApplicationVo> findAdminLiveApplicationVos(Long liveId, Boolean isCanceled) {
        return liveApplicationRepository.findAdminLiveApplicationVos(liveId, isCanceled);
    }

    public LiveApplication createLiveApplicationAndSave(CreateApplicationRequestDto requestDto, Live live, User user) {
        LiveApplication newLiveApplication = LiveApplication.createLiveApplication(requestDto, live, user);
        return liveApplicationRepository.save(newLiveApplication);
    }

    public void validateExistingApplication(Long liveId, Long userId) {
        Optional<LiveApplication> liveApplication = liveApplicationRepository.findLiveApplicationByLiveIdAndUserId(liveId, userId);
        if (liveApplication.isPresent())
            throw new ConflictException(CONFLICT_APPLICATION);
    }

    public void validateLiveDuration(Live live) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(live.getDeadline()))
            throw new InvalidValueException(INVALID_APPLICATION_TIME);
    }

    public LiveApplication findLiveApplicationByIdOrThrow(Long applicationId) {
        return liveApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(APPLICATION_NOT_FOUND));
    }

    public Boolean existLiveApplicationByLiveIdAndUserId(Long liveId, Long userId) {
        LiveApplication liveApplication = liveApplicationRepository.findLiveApplicationByLiveIdAndUserId(liveId, userId).orElse(null);
        return !Objects.isNull(liveApplication);
    }

    public Long countLiveApplications(Long liveId) {
        return liveApplicationRepository.countByLiveId(liveId);
    }

    public void deleteLiveApplication(LiveApplication liveApplication) {
        liveApplicationRepository.delete(liveApplication);
    }

    public Boolean checkExistingLiveApplication(User user, Long liveId) {
        if (Objects.isNull(user)) return null;
        return liveApplicationRepository.findLiveApplicationIdByUserIdAndLiveId(user.getId(), liveId).isPresent();
    }

    public List<String> findQuestionListByLiveId(Long liveId) {
        return liveApplicationRepository.findQuestionListByLiveId(liveId);
    }

    public List<String> findMotivateListByLiveId(Long liveId) {
        return liveApplicationRepository.findMotivateListByLiveId(liveId);
    }

    public List<ReviewNotificationUserVo> getReviewNotificationUserVos(Long liveId) {
        return liveApplicationRepository.findAllReviewNotificationUserVo(liveId);
    }

    public List<User> getRemindNotificationUsers(Long liveId) {
        return liveApplicationRepository.findAllRemindNotificationUser(liveId);
    }
}
