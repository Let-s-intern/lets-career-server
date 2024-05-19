package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;

import java.util.List;

public interface ApplicationQueryRepository {
    List<MyApplicationVo> findMyApplications(Long userId);
}
