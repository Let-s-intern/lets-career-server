package org.letscareer.letscareer.domain.challlengenotice.repository;

import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeVo;

import java.util.List;

public interface ChallengeNoticeQueryRepository {
    List<ChallengeNoticeVo> findAllChallengeNoticeVos(Long challengeId);
}
