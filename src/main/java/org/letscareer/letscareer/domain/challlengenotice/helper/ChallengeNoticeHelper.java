package org.letscareer.letscareer.domain.challlengenotice.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challlengenotice.entity.ChallengeNotice;
import org.letscareer.letscareer.domain.challlengenotice.repository.ChallengeNoticeRepository;
import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ChallengeNoticeHelper {
    private final ChallengeNoticeRepository challengeNoticeRepository;

    public void saveChallengeNotice(ChallengeNotice challengeNotice) {
        challengeNoticeRepository.save(challengeNotice);
    }

    public ChallengeNotice findChallengeNoticeOrThrow(Long challengeNoticeId) {
        return challengeNoticeRepository.findById(challengeNoticeId).orElseThrow(EntityNotFoundException::new);
    }

    public List<ChallengeNoticeVo> findAllChallengeNoticeVos(Long challengeId) {
        return challengeNoticeRepository.findAllChallengeNoticeVos(challengeId);
    }

    public void deleteChallengeNotice(ChallengeNotice challengeNotice) {
        challengeNoticeRepository.delete(challengeNotice);
    }
}
