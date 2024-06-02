package org.letscareer.letscareer.domain.program.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.helper.ChallengeClassificationHelper;
import org.letscareer.letscareer.domain.classification.helper.LiveClassificationHelper;
import org.letscareer.letscareer.domain.classification.helper.VodClassificationHelper;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramForConditionResponseDto;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramsForConditionResponseDto;
import org.letscareer.letscareer.domain.program.entity.SearchCondition;
import org.letscareer.letscareer.domain.program.helper.ProgramHelper;
import org.letscareer.letscareer.domain.program.mapper.ProgramMapper;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.vo.ProgramForConditionVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ProgramServiceImpl implements ProgramService {
    private final ProgramHelper programHelper;
    private final ProgramMapper programMapper;
    private final ChallengeClassificationHelper challengeClassificationHelper;
    private final LiveClassificationHelper liveClassificationHelper;
    private final VodClassificationHelper vodClassificationHelper;

    @Override
    public GetProgramsForConditionResponseDto getProgramsForCondition(List<ProgramType> type,
                                                                      List<ProgramClassification> typeList,
                                                                      List<ProgramStatusType> statusList,
                                                                      LocalDateTime startDate,
                                                                      LocalDateTime endDate,
                                                                      Pageable pageable) {
        SearchCondition condition = SearchCondition.of(type, typeList, statusList, startDate, endDate, pageable);
        Page<ProgramForConditionVo> programForConditionVo = programHelper.findProgramForConditionVos(condition);
        List<GetProgramForConditionResponseDto<?>> conditionResponseDtoList
                = composeProgramForConditionVosAndClassifications(programForConditionVo.getContent());
        PageInfo pageInfo = PageInfo.of(programForConditionVo);
        return programMapper.toGetProgramsForConditionResponseDto(conditionResponseDtoList, pageInfo);
    }

    private List<GetProgramForConditionResponseDto<?>> composeProgramForConditionVosAndClassifications(List<ProgramForConditionVo> programForConditionVo) {
        return programForConditionVo.stream()
                .map(this::createGetProgramForDurationResponseDto)
                .collect(Collectors.toList());
    }

    private GetProgramForConditionResponseDto<?> createGetProgramForDurationResponseDto(ProgramForConditionVo programVo) {
        List<?> classificationList = getProgramClassificationsForType(programVo.programType(), programVo.id());
        return programMapper.toGetProgramForDurationResponseDto(programVo, classificationList);
    }

    private List<?> getProgramClassificationsForType(ProgramType type, Long programId) {
        if (ProgramType.CHALLENGE.equals(type))
            return challengeClassificationHelper.findClassificationDetailVos(programId);
        else if (ProgramType.LIVE.equals(type))
            return liveClassificationHelper.findLiveClassificationVos(programId);
        else if (ProgramType.VOD.equals(type))
            return vodClassificationHelper.findVodClassificationVos(programId);
        return null;
    }
}
