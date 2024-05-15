package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.repository.LiveApplicationRepository;
import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LiveApplicationHelper {
    private final LiveApplicationRepository liveApplicationRepository;

    public List<AdminLiveApplicationVo> findAdminLiveApplicationVos(Long liveId, Boolean isConfirmed) {
        return liveApplicationRepository.findAdminLiveApplicationVos(liveId, isConfirmed);
    }
}
