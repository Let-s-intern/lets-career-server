package org.letscareer.letscareer.domain.challengeoption.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeoption.dto.request.CreateChallengeOptionRequestDto;
import org.letscareer.letscareer.domain.challengeoption.entity.ChallengeOption;
import org.letscareer.letscareer.domain.challengeoption.repository.ChallengeOptionRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChallengeOptionHelper {
    private final ChallengeOptionRepository challengeOptionRepository;

    public void createChallengeOptionAndSave(CreateChallengeOptionRequestDto requestDto) {
        ChallengeOption challengeOption = ChallengeOption.createChallengeOption(requestDto);
        challengeOptionRepository.save(challengeOption);
    }
}
