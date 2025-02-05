package org.letscareer.letscareer.domain.program.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.admincalssification.helper.ChallengeAdminClassificationHelper;
import org.letscareer.letscareer.domain.admincalssification.helper.LiveAdminClassificationHelper;
import org.letscareer.letscareer.domain.admincalssification.helper.VodAdminClassificationHelper;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeRecommendVo;
import org.letscareer.letscareer.domain.classification.helper.ChallengeClassificationHelper;
import org.letscareer.letscareer.domain.classification.helper.LiveClassificationHelper;
import org.letscareer.letscareer.domain.classification.helper.VodClassificationHelper;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.live.vo.LiveRecommendVo;
import org.letscareer.letscareer.domain.program.dto.response.*;
import org.letscareer.letscareer.domain.program.entity.SearchCondition;
import org.letscareer.letscareer.domain.program.helper.ProgramHelper;
import org.letscareer.letscareer.domain.program.mapper.ProgramMapper;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.vo.ProgramForAdminVo;
import org.letscareer.letscareer.domain.program.vo.ProgramForConditionVo;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.vo.ReportRecommendVo;
import org.letscareer.letscareer.domain.vod.helper.VodHelper;
import org.letscareer.letscareer.domain.vod.vo.VodDetailVo;
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
    private final ChallengeHelper challengeHelper;
    private final ChallengeClassificationHelper challengeClassificationHelper;
    private final ChallengeAdminClassificationHelper challengeAdminClassificationHelper;
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final LiveHelper liveHelper;
    private final LiveClassificationHelper liveClassificationHelper;
    private final LiveAdminClassificationHelper liveAdminClassificationHelper;
    private final LiveApplicationHelper liveApplicationHelper;
    private final VodHelper vodHelper;
    private final VodClassificationHelper vodClassificationHelper;
    private final VodAdminClassificationHelper vodAdminClassificationHelper;
    private final ReportHelper reportHelper;

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

    @Override
    public GetProgramsForAdminResponseDto getProgramsForAdmin(List<ProgramType> type,
                                                              List<ProgramClassification> typeList,
                                                              List<ProgramStatusType> statusList,
                                                              LocalDateTime startDate,
                                                              LocalDateTime endDate,
                                                              Pageable pageable) {
        SearchCondition condition = SearchCondition.of(type, typeList, statusList, startDate, endDate, pageable);
        Page<ProgramForAdminVo> programForAdminVos = programHelper.findProgramForAdminVos(condition);
        List<GetProgramWithCurrentCountResponseDto> vos = createGetProgramWithCurrentCountResponseDto(programForAdminVos.getContent());
        List<GetProgramForAdminResponseDto<?>> conditionResponseDtoList = composeProgramForAdminVosAndClassifications(vos);
        PageInfo pageInfo = PageInfo.of(programForAdminVos);
        return programMapper.toGetProgramsForAdminResponseDto(conditionResponseDtoList, pageInfo);
    }

    @Override
    public GetProgramsForRecommendResponseDto getProgramsForRecommend() {
        List<ChallengeRecommendVo> challengeList = challengeHelper.findAllChallengeRecommendVos();
        LiveRecommendVo live = liveHelper.findLiveRecommendVo();
        List<VodDetailVo> vodList = vodHelper.findAllVodRecommendVos();
        List<ReportRecommendVo> reportList = reportHelper.findAllReportRecommendVos();
        return programMapper.toGetProgramsForRecommendResponseDto(challengeList, live, vodList, reportList);
    }

    private List<GetProgramWithCurrentCountResponseDto> createGetProgramWithCurrentCountResponseDto(List<ProgramForAdminVo> vos) {
        return vos.stream()
                .map(vo -> programMapper.toGetProgramWithCurrentCountResponseDto(vo, getApplicationCount(vo)))
                .collect(Collectors.toList());
    }

    private Long getApplicationCount(ProgramForAdminVo vo) {
        if (vo.programType().equals(ProgramType.CHALLENGE))
            return challengeApplicationHelper.countChallengeApplications(vo.id());
        else if (vo.programType().equals(ProgramType.LIVE))
            return liveApplicationHelper.countLiveApplications(vo.id());
        return 0L;
    }

    private List<GetProgramForConditionResponseDto<?>> composeProgramForConditionVosAndClassifications(List<ProgramForConditionVo> programForConditionVo) {
        return programForConditionVo.stream()
                .map(this::createGetProgramForDurationResponseDto)
                .collect(Collectors.toList());
    }

    private List<GetProgramForAdminResponseDto<?>> composeProgramForAdminVosAndClassifications(List<GetProgramWithCurrentCountResponseDto> vos) {
        return vos.stream()
                .map(this::createGetProgramForAdminResponseDto)
                .collect(Collectors.toList());
    }

    private GetProgramForConditionResponseDto<?> createGetProgramForDurationResponseDto(ProgramForConditionVo programVo) {
        List<?> classificationList = getProgramClassificationsForType(programVo.programType(), programVo.id());
        return programMapper.toGetProgramForDurationResponseDto(programVo, classificationList);
    }

    private GetProgramForAdminResponseDto<?> createGetProgramForAdminResponseDto(GetProgramWithCurrentCountResponseDto programVo) {
        List<?> classificationList = getProgramClassificationsForType(programVo.programType(), programVo.id());
        List<?> adminClassificationList = getProgramAdminClassificationsForType(programVo.programType(), programVo.id());
        return programMapper.toGetProgramForAdminResponseDto(programVo, classificationList, adminClassificationList);
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

    private List<?> getProgramAdminClassificationsForType(ProgramType type, Long programId) {
        if (ProgramType.CHALLENGE.equals(type))
            return challengeAdminClassificationHelper.findAdminClassificationDetailVos(programId);
        else if (ProgramType.LIVE.equals(type))
            return liveAdminClassificationHelper.findAdminClassificationDetailVos(programId);
        else if (ProgramType.VOD.equals(type))
            return vodAdminClassificationHelper.findAdminClassificationDetailVos(programId);
        return null;
    }
}
