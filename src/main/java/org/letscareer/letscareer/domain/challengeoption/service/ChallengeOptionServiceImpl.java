package org.letscareer.letscareer.domain.challengeoption.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeoption.dto.request.CreateChallengeOptionRequestDto;
import org.letscareer.letscareer.domain.challengeoption.dto.response.GetAllChallengeOptionListResponseDto;
import org.letscareer.letscareer.domain.challengeoption.helper.ChallengeOptionHelper;
import org.letscareer.letscareer.domain.challengeoption.vo.ChallengeOptionAdminVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ChallengeOptionServiceImpl implements ChallengeOptionService {
    private final ChallengeOptionHelper challengeOptionHelper;

    @Override
    public GetAllChallengeOptionListResponseDto getChallengeOptionList() {
        List<ChallengeOptionAdminVo> challengeOptionAdminVos = challengeOptionHelper.findAllChallengeOptionAdminVos();
        return GetAllChallengeOptionListResponseDto.of(challengeOptionAdminVos);
    }

    public void createChallengeOption(CreateChallengeOptionRequestDto requestDto) {
        challengeOptionHelper.createChallengeOptionAndSave(requestDto);
    }
}
