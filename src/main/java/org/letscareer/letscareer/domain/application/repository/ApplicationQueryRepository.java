package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.type.ApplicationStatus;
import org.letscareer.letscareer.domain.application.vo.MyApplicationVo;

import java.util.List;
import java.util.Optional;

public interface ApplicationQueryRepository {
    List<MyApplicationVo> findMyApplications(Long userId, ApplicationStatus status);

    Optional<Long> findPriceIdByApplicationId(Long applicationId);
}
