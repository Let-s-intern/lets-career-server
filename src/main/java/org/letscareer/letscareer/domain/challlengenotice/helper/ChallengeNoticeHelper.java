package org.letscareer.letscareer.domain.challlengenotice.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challlengenotice.entity.ChallengeNotice;
import org.letscareer.letscareer.domain.challlengenotice.repository.ChallengeNoticeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class ChallengeNoticeHelper {
    private final ChallengeNoticeRepository challengeNoticeRepository;

    public void saveChallengeNotice(ChallengeNotice challengeNotice) {
        challengeNoticeRepository.save(challengeNotice);
    }
}
