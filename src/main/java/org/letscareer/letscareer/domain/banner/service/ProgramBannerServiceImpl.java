package org.letscareer.letscareer.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerDetailResponseDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerListResponseDto;
import org.letscareer.letscareer.domain.banner.entity.ProgramBanner;
import org.letscareer.letscareer.domain.banner.helper.ProgramBannerHelper;
import org.letscareer.letscareer.domain.banner.mapper.BannerMapper;
import org.letscareer.letscareer.domain.banner.mapper.ProgramBannerMapper;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminDetailVo;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.letscareer.letscareer.domain.banner.vo.BannerUserVo;
import org.letscareer.letscareer.domain.file.entity.File;
import org.letscareer.letscareer.domain.file.helper.FileHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service("PROGRAM")
public class ProgramBannerServiceImpl implements BannerService {
    private static final String PROGRAM_BANNER_S3_PATH = "banner/program/";
    private final ProgramBannerHelper programBannerHelper;
    private final ProgramBannerMapper programBannerMapper;
    private final BannerMapper bannerMapper;
    private final FileHelper fileHelper;

    @Override
    public void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto, MultipartFile file, MultipartFile mobileFile) {
        File newFile = fileHelper.createFileAndSave(PROGRAM_BANNER_S3_PATH, file);
        File newMobileFile = fileHelper.createFileAndSave(PROGRAM_BANNER_S3_PATH, mobileFile);
        ProgramBanner newProgramBanner = programBannerMapper.toEntity(type, createBannerRequestDto, newFile, newMobileFile);
        programBannerHelper.saveProgramBanner(newProgramBanner);
    }

    @Override
    public BannerListResponseDto getBanners() {
        List<BannerUserVo> bannerUserVos = programBannerHelper.findAllUserBannerAdminVos();
        return bannerMapper.toBannerAdminListResponseDto(bannerUserVos);
    }

    @Override
    public BannerListResponseDto getBannersForAdmin() {
        List<BannerAdminVo> programBannerAdminList = programBannerHelper.findAllProgramBannerAdminVos();
        return bannerMapper.toBannerAdminListResponseDto(programBannerAdminList);
    }

    @Override
    public BannerDetailResponseDto getBannerDetail(Long bannerId) {
        BannerAdminDetailVo bannerAdminDetailVo = programBannerHelper.findBannerAdminDetailVoOrThrow(bannerId);
        return bannerMapper.toBannerDetailResponseDto(bannerAdminDetailVo);
    }

    @Override
    public void updateBanner(Long bannerId, UpdateBannerRequestDto updateBannerRequestDto, MultipartFile file, MultipartFile mobileFile) {
        ProgramBanner programBanner = programBannerHelper.findByIdOrThrow(bannerId);
        updateFile(programBanner, file);
        updateMobileFile(programBanner, mobileFile);
        programBanner.updateProgramBanner(updateBannerRequestDto);
    }

    @Override
    public void deleteBanner(Long bannerId) {
        ProgramBanner programBanner = programBannerHelper.findByIdOrThrow(bannerId);
        programBannerHelper.deleteProgramBanner(programBanner);
    }

    private void updateFile(ProgramBanner programBanner, MultipartFile file) {
        if (Objects.isNull(file)) return;
        if (programBanner.getFile() != null) fileHelper.deleteFile(programBanner.getFile());
        File newFile = fileHelper.createFileAndSave(PROGRAM_BANNER_S3_PATH, file);
        programBanner.updateFile(newFile);
    }

    private void updateMobileFile(ProgramBanner programBanner, MultipartFile file) {
        if (Objects.isNull(file)) return;
        if (programBanner.getMobileFile() != null) fileHelper.deleteFile(programBanner.getMobileFile());
        File newFile = fileHelper.createFileAndSave(PROGRAM_BANNER_S3_PATH, file);
        programBanner.updateMobileFile(newFile);
    }
}
