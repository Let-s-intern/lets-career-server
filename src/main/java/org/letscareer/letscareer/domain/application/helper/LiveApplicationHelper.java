package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.application.repository.LiveApplicationRepository;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public Optional<LiveApplication> findLiveApplicationByLiveIdAndUserId(Long liveId, Long userId) {
        return liveApplicationRepository.findLiveApplicationByLiveIdAndUserId(liveId, userId);
    }
}
