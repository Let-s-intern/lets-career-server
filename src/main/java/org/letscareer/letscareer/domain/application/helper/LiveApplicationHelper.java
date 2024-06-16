package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.application.repository.LiveApplicationRepository;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.vo.LiveEmailVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.APPLICATION_NOT_FOUND;
import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.CONFLICT_APPLICATION;

@RequiredArgsConstructor
@Component
public class LiveApplicationHelper {
    private final LiveApplicationRepository liveApplicationRepository;

    public List<AdminLiveApplicationVo> findAdminLiveApplicationVos(Long liveId, Boolean isConfirmed) {
        return liveApplicationRepository.findAdminLiveApplicationVos(liveId, isConfirmed);
    }

    public LiveApplication createLiveApplicationAndSave(CreateApplicationRequestDto requestDto, Live live, User user) {
        LiveApplication newLiveApplication = LiveApplication.createLiveApplication(requestDto, live, user);
        return liveApplicationRepository.save(newLiveApplication);
    }

    public void validateExistingApplication(Long liveId, Long userId) {
        Optional<LiveApplication> liveApplication = liveApplicationRepository.findLiveApplicationByLiveIdAndUserId(liveId, userId);
        if(liveApplication.isPresent()) throw new ConflictException(CONFLICT_APPLICATION);
    }

    public LiveApplication findLiveApplicationByIdOrThrow(Long applicationId) {
        return liveApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(APPLICATION_NOT_FOUND));
    }

    public void deleteLiveApplication(LiveApplication liveApplication) {
        liveApplicationRepository.delete(liveApplication);
    }

    public Boolean checkExistingLiveApplication(User user, Long liveId) {
        if (Objects.isNull(user)) return null;
        return liveApplicationRepository.findLiveApplicationIdByUserIdAndLiveId(user.getId(), liveId).isPresent();
    }

    public LiveEmailVo findLiveEmailVo(Long applicationId) {
        return liveApplicationRepository.findLiveEmailVoByApplicationId(applicationId);
    }

    public List<String> findEmailListByLiveId(Long liveId) {
        return liveApplicationRepository.findEmailListByLiveId(liveId);
    }
}
