package org.letscareer.letscareer.domain.challlengenotice.repository;

import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeAdminVo;

import java.util.List;

public interface ChallengeNoticeQueryRepository {
    List<ChallengeNoticeAdminVo> findAllChallengeNoticeAdminVos(Long challengeId);
}
