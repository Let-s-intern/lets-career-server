package org.letscareer.letscareer.domain.faq.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.faq.dto.request.CreateFaqRequestDto;
import org.letscareer.letscareer.domain.faq.entity.Faq;
import org.letscareer.letscareer.domain.faq.entity.FaqChallenge;
import org.letscareer.letscareer.domain.faq.entity.FaqLive;
import org.letscareer.letscareer.domain.faq.repository.FaqChallengeRepository;
import org.letscareer.letscareer.domain.faq.repository.FaqLiveRepository;
import org.letscareer.letscareer.domain.faq.repository.FaqRepository;
import org.letscareer.letscareer.domain.faq.type.FaqProgramType;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.faq.error.FaqErrorCode.FAQ_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class FaqHelper {
    private final FaqRepository faqRepository;
    private final FaqChallengeRepository faqChallengeRepository;
    private final FaqLiveRepository faqLiveRepository;

    public FaqChallenge createFaqChallengeAndSave(Faq faq,
                                                  Challenge challenge) {
        FaqChallenge faqChallenge = FaqChallenge.createFaqChallenge(faq, challenge);
        return faqChallengeRepository.save(faqChallenge);
    }

    public FaqLive createFaqLiveAndSave(Faq faq,
                                        Live live) {
        FaqLive faqLive = FaqLive.createFaqLive(faq, live);
        return faqLiveRepository.save(faqLive);
    }

    public Faq createFaqAndSave(CreateFaqRequestDto requestDto) {
        Faq faq = Faq.createFaq(requestDto);
        return faqRepository.save(faq);
    }

    public Faq findFaqByIdAndThrow(Long faqId) {
        return faqRepository.findById(faqId)
                .orElseThrow(() -> new EntityNotFoundException(FAQ_NOT_FOUND));
    }

    public List<FaqDetailVo> findChallengeFaqDetailVos(Long challengeId) {
        return faqRepository.findChallengeFaqDetailVos(challengeId);
    }

    public List<FaqDetailVo> findLiveFaqDetailVos(Long liveId) {
        return faqRepository.findLiveFaqDetailVos(liveId);
    }

    public List<FaqDetailVo> findFaqDetailVosByType(FaqProgramType type) {
        return faqRepository.findFaqDetailVosForType(type);
    }

    public void deleteChallengeFaqsByChallengeId(Long challengeId) {
        faqChallengeRepository.deleteAllByChallengeId(challengeId);
    }

    public void deleteLiveFaqsByLiveId(Long liveId) {
        faqLiveRepository.deleteAllByLiveId(liveId);
    }

    public void deleteFaqById(Long faqId) {
        faqRepository.deleteById(faqId);
    }
}
