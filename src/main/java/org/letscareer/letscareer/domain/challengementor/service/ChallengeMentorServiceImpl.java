package org.letscareer.letscareer.domain.challengementor.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengementor.dto.response.GetMyChallengeMentorsResponseDto;
import org.letscareer.letscareer.domain.challengementor.helper.ChallengeMentorHelper;
import org.letscareer.letscareer.domain.challengementor.vo.MyChallengeMentorVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.letscareer.letscareer.domain.challengementor.error.ChallengeMentorErrorCode.NOT_CHALLENGE_MENTOR;

@RequiredArgsConstructor
@Transactional
@Service
public class ChallengeMentorServiceImpl implements ChallengeMentorService {
    private final ChallengeMentorHelper challengeMentorHelper;

    @Override
    public GetMyChallengeMentorsResponseDto getMyChallengeMentors(User mentor) {
        if(!mentor.getIsMentor()) throw new UnauthorizedException(NOT_CHALLENGE_MENTOR);
        List<MyChallengeMentorVo> myChallengeMentorVoList = challengeMentorHelper.findAllMyChallengeMentorVosByMentorId(mentor.getId());
        return GetMyChallengeMentorsResponseDto.of(myChallengeMentorVoList);
    }

    @Override
    public void deleteChallengeMentor(Long challengeMentorId) {
        challengeMentorHelper.deleteChallengeMentorById(challengeMentorId);
    }
}
