package org.letscareer.letscareer.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerAdminListResponseDto;
import org.letscareer.letscareer.domain.banner.entity.ProgramBanner;
import org.letscareer.letscareer.domain.banner.helper.ProgramBannerHelper;
import org.letscareer.letscareer.domain.banner.mapper.BannerMapper;
import org.letscareer.letscareer.domain.banner.mapper.ProgramBannerMapper;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("PROGRAM")
public class ProgramBannerServiceImpl implements BannerService {
    private final ProgramBannerHelper programBannerHelper;
    private final ProgramBannerMapper programBannerMapper;
    private final BannerMapper bannerMapper;

    @Override
    public void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        ProgramBanner newProgramBanner = programBannerMapper.toEntity(type, createBannerRequestDto);
        programBannerHelper.saveProgramBanner(newProgramBanner);
    }

    @Override
    public BannerAdminListResponseDto getBannersForAdmin() {
        List<BannerAdminVo> programBannerAdminList = programBannerHelper.findAllProgramBannerAdminVos();
        return bannerMapper.toBannerAdminListResponseDto(programBannerAdminList);
    }
}
