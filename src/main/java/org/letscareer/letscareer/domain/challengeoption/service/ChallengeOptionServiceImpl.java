package org.letscareer.letscareer.domain.challengeoption.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeoption.dto.request.CreateChallengeOptionRequestDto;
import org.letscareer.letscareer.domain.challengeoption.helper.ChallengeOptionHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ChallengeOptionServiceImpl implements ChallengeOptionService {
    private final ChallengeOptionHelper challengeOptionHelper;

    public void createChallengeOption(CreateChallengeOptionRequestDto requestDto) {
        challengeOptionHelper.createChallengeOptionAndSave(requestDto);
    }
}
