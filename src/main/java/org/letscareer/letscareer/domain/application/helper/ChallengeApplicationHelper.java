package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.repository.ChallengeApplicationRepository;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Component
public class ChallengeApplicationHelper {
    private final ChallengeApplicationRepository challengeApplicationRepository;

    public List<AdminChallengeApplicationVo> findAdminChallengeApplicationVos(Long challengeId, Boolean isConfirmed) {
        return challengeApplicationRepository.findAdminChallengeApplicationVos(challengeId, isConfirmed);
    }
}
