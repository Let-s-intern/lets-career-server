package org.letscareer.letscareer.domain.challengeoption.repository;

import org.letscareer.letscareer.domain.challengeoption.vo.ChallengeOptionAdminVo;

import java.util.List;

public interface ChallengeOptionQueryRepository {
    List<ChallengeOptionAdminVo> findAllChallengeOptionAdminVos();
}
