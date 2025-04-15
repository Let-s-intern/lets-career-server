package org.letscareer.letscareer.domain.challlengenotice.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challlengenotice.entity.ChallengeNotice;
import org.letscareer.letscareer.domain.challlengenotice.repository.ChallengeNoticeRepository;
import org.letscareer.letscareer.domain.challlengenotice.vo.ChallengeNoticeVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ChallengeNoticeVo> findAllChallengeNoticeVos(Long challengeId, Pageable pageable) {
        return challengeNoticeRepository.findAllChallengeNoticeVos(challengeId, pageable);
    }

    public List<ChallengeNotice> findAllByChallengeId(Long challengeId) {
        return challengeNoticeRepository.findAllByChallengeId(challengeId);
    }

    public void deleteChallengeNotice(ChallengeNotice challengeNotice) {
        challengeNoticeRepository.delete(challengeNotice);
    }

    public void deleteAllByChallengeId(Long challengeId) {
        challengeNoticeRepository.deleteAllByChallengeId(challengeId);
    }
}
