package org.letscareer.letscareer.domain.challengeoption.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeoption.dto.request.CreateChallengeOptionRequestDto;
import org.letscareer.letscareer.domain.challengeoption.entity.ChallengeOption;
import org.letscareer.letscareer.domain.challengeoption.repository.ChallengeOptionRepository;
import org.letscareer.letscareer.domain.challengeoption.vo.ChallengeOptionAdminVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.challengeoption.error.ChallengeOptionErrorCode.CHALLENGE_OPTION_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ChallengeOptionHelper {
    private final ChallengeOptionRepository challengeOptionRepository;

    public void createChallengeOptionAndSave(CreateChallengeOptionRequestDto requestDto) {
        ChallengeOption challengeOption = ChallengeOption.createChallengeOption(requestDto);
        challengeOptionRepository.save(challengeOption);
    }

    public List<ChallengeOptionAdminVo> findAllChallengeOptionAdminVos() {
        return challengeOptionRepository.findAllChallengeOptionAdminVos();
    }

    public ChallengeOption findChallengeOptionByChallengeOptionIdOrThrow(Long challengeOptionId) {
        return challengeOptionRepository.findById(challengeOptionId).orElseThrow(() -> new EntityNotFoundException(CHALLENGE_OPTION_NOT_FOUND));
    }

    public List<ChallengeOption> findAllChallengeOptionById(List<Long> challengeOptionIdList) {
        return challengeOptionRepository.findAllById(challengeOptionIdList);
    }
}
