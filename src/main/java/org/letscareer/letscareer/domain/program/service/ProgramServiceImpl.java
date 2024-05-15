package org.letscareer.letscareer.domain.program.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.helper.ChallengeClassificationHelper;
import org.letscareer.letscareer.domain.classification.helper.LiveClassificationHelper;
import org.letscareer.letscareer.domain.classification.helper.VodClassificationHelper;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramResponseDto;
import org.letscareer.letscareer.domain.program.dto.response.GetProgramsResponseDto;
import org.letscareer.letscareer.domain.program.helper.ProgramHelper;
import org.letscareer.letscareer.domain.program.mapper.ProgramMapper;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.vo.AdminProgramVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public GetProgramsResponseDto getPrograms(Pageable pageable) {
        Page<AdminProgramVo> adminProgramVos = programHelper.findAdminProgramVos(pageable);
        List<GetProgramResponseDto<?>> contents = composeProgramVosAndClassifications(adminProgramVos);
        PageInfo pageInfo = PageInfo.of(adminProgramVos);
        return programMapper.toGetProgramsResponseDto(contents, pageInfo);
    }

    private List<GetProgramResponseDto<?>> composeProgramVosAndClassifications(Page<AdminProgramVo> adminProgramVos) {
        List<AdminProgramVo> contents = adminProgramVos.getContent();
        return contents.stream()
                .map(this::createGetProgramResponseDto)
                .collect(Collectors.toList());
    }

    private GetProgramResponseDto<?> createGetProgramResponseDto(AdminProgramVo content) {
        List<?> classificationList = getProgramClassificationsForType(content.programType(), content.id());
        return programMapper.toGetProgramResponseDto(content, classificationList);
    }

    private List<?> getProgramClassificationsForType(ProgramType type, Long programId) {
        System.out.println(type + " " + programId);
        if (ProgramType.CHALLENGE.equals(type))
            return challengeClassificationHelper.findClassificationDetailVos(programId);
        else if (ProgramType.LIVE.equals(type))
            return liveClassificationHelper.findLiveClassificationVos(programId);
        else if (ProgramType.VOD.equals(type))
            return vodClassificationHelper.findVodClassificationVos(programId);
        return null;
    }
}
