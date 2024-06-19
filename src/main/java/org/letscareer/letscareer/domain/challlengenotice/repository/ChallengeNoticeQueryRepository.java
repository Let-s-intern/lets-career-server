package org.letscareer.letscareer.domain.challlengenotice.repository;

import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChallengeNoticeQueryRepository {
    Page<ChallengeNoticeVo> findAllChallengeNoticeVos(Long challengeId, Pageable pageable);
}
