package org.letscareer.letscareer.domain.challengeoption.service;

import org.letscareer.letscareer.domain.challengeoption.dto.request.CreateChallengeOptionRequestDto;
import org.letscareer.letscareer.domain.challengeoption.dto.response.GetAllChallengeOptionListResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ChallengeOptionService {
    GetAllChallengeOptionListResponseDto getChallengeOptionList();
    void createChallengeOption(CreateChallengeOptionRequestDto requestDto);
}
