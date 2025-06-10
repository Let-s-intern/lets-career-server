package org.letscareer.letscareer.domain.challengementor.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengementor.helper.ChallengeMentorHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ChallengeMentorServiceImpl implements ChallengeMentorService {
    private final ChallengeMentorHelper challengeMentorHelper;

    @Override
    public void deleteChallengeMentor(Long challengeMentorId) {
        challengeMentorHelper.deleteChallengeMentorById(challengeMentorId);
    }
}
