package org.letscareer.letscareer.domain.missiontemplate.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.CreateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.UpdateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.response.MissionTemplateAdminListResponseDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.response.MissionTemplateAdminSimpleListResponseDto;
import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.letscareer.letscareer.domain.missiontemplate.helper.MissionTemplateHelper;
import org.letscareer.letscareer.domain.missiontemplate.mapper.MissionTemplateMapper;
import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminSimpleVo;
import org.letscareer.letscareer.domain.missiontemplate.vo.MissionTemplateAdminVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MissionTemplateServiceImpl implements MissionTemplateService {
    private final MissionTemplateHelper missionTemplateHelper;
    private final MissionTemplateMapper missionTemplateMapper;

    @Override
    public void createMissionTemplate(CreateMissionTemplateRequestDto createMissionTemplateRequestDto) {
        MissionTemplate newMissionTemplate = missionTemplateMapper.toEntity(createMissionTemplateRequestDto);
        missionTemplateHelper.saveMissionTemplate(newMissionTemplate);
    }

    @Override
    public void updateMissionTemplate(Long missionTemplateId, UpdateMissionTemplateRequestDto updateMissionTemplateRequestDto) {
        MissionTemplate missionTemplate = missionTemplateHelper.findMissionTemplateByIdOrThrow(missionTemplateId);
        missionTemplate.updateMissionTemplate(updateMissionTemplateRequestDto);
    }

    @Override
    public void deleteMissionTemplate(Long missionTemplateId) {
        MissionTemplate missionTemplate = missionTemplateHelper.findMissionTemplateByIdOrThrow(missionTemplateId);
        missionTemplateHelper.deleteMissionTemplate(missionTemplate);
    }

    @Override
    public MissionTemplateAdminListResponseDto getMissionTemplatesForAdmin() {
        List<MissionTemplateAdminVo> missionTemplateAdminList = missionTemplateHelper.findAllMissionTemplateAdminVos();
        return missionTemplateMapper.toMissionTemplateAdminListResponseDto(missionTemplateAdminList);
    }

    @Override
    public MissionTemplateAdminSimpleListResponseDto getSimpleMissionTemplatesForAdmin() {
        List<MissionTemplateAdminSimpleVo> missionTemplateAdminSimpleList = missionTemplateHelper.findAllMissionTemplateAdminSimpleVos();
        return missionTemplateMapper.toMissionTemplateAdminSimpleListResponseDto(missionTemplateAdminSimpleList);
    }

}
