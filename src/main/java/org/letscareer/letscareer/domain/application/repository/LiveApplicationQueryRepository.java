package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.vo.AdminLiveApplicationVo;

import java.util.List;

public interface LiveApplicationQueryRepository {
    List<AdminLiveApplicationVo> findAdminLiveApplicationVos(Long liveId, Boolean isConfirmed);
}
