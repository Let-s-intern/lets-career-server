package org.letscareer.letscareer.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerDetailResponseDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerListResponseDto;
import org.letscareer.letscareer.domain.banner.entity.MainBottomBanner;
import org.letscareer.letscareer.domain.banner.helper.MainBottomBannerHelper;
import org.letscareer.letscareer.domain.banner.mapper.BannerMapper;
import org.letscareer.letscareer.domain.banner.mapper.MainBottomBannerMapper;
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
@Service("MAIN_BOTTOM")
public class MainBottomBannerServiceImpl implements BannerService {
    private static final String MAIN_BOTTOM_BANNER_S3_PATH = "banner/main-bottom/";
    private final MainBottomBannerHelper mainBottomBannerHelper;
    private final MainBottomBannerMapper mainBottomBannerMapper;
    private final BannerMapper bannerMapper;
    private final FileHelper fileHelper;

    @Override
    public void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto, MultipartFile file, MultipartFile mobileFile) {
        File newFile = fileHelper.createFileAndSave(MAIN_BOTTOM_BANNER_S3_PATH, file);
        File newMobileFile = fileHelper.createFileAndSave(MAIN_BOTTOM_BANNER_S3_PATH, mobileFile);
        MainBottomBanner newMainBottomBanner = mainBottomBannerMapper.toEntity(type, createBannerRequestDto, newFile, newMobileFile);
        mainBottomBannerHelper.saveMainBottomBanner(newMainBottomBanner);
    }

    @Override
    public BannerListResponseDto getBanners() {
        List<BannerUserVo> bannerUserVos = mainBottomBannerHelper.findAllUserBannerAdminVos();
        return bannerMapper.toBannerAdminListResponseDto(bannerUserVos);
    }

    @Override
    public BannerListResponseDto getBannersForAdmin() {
        List<BannerAdminVo> mainBottomBannerAdminList = mainBottomBannerHelper.findAllMainBottomBannerAdminVos();
        return bannerMapper.toBannerAdminListResponseDto(mainBottomBannerAdminList);
    }

    @Override
    public BannerDetailResponseDto getBannerDetail(Long bannerId) {
        BannerAdminDetailVo bannerAdminDetailVo = mainBottomBannerHelper.findBannerAdminDetailVoByIdOrThrow(bannerId);
        return bannerMapper.toBannerDetailResponseDto(bannerAdminDetailVo);
    }

    @Override
    public void updateBanner(Long bannerId, UpdateBannerRequestDto updateBannerRequestDto, MultipartFile file, MultipartFile mobileFile) {
        MainBottomBanner mainBottomBanner = mainBottomBannerHelper.findByIdOrThrow(bannerId);
        updateFile(mainBottomBanner, file);
        updateMobileFile(mainBottomBanner, mobileFile);
        mainBottomBanner.updateMainBottomBanner(updateBannerRequestDto);
    }

    @Override
    public void deleteBanner(Long bannerId) {
        MainBottomBanner mainBottomBanner = mainBottomBannerHelper.findByIdOrThrow(bannerId);
        mainBottomBannerHelper.deleteMainBottomBanner(mainBottomBanner);
    }

    private void updateFile(MainBottomBanner mainBottomBanner, MultipartFile file) {
        if (Objects.isNull(file)) return;
        if (mainBottomBanner.getFile() != null) fileHelper.deleteFile(mainBottomBanner.getFile());
        File newFile = fileHelper.createFileAndSave(MAIN_BOTTOM_BANNER_S3_PATH, file);
        mainBottomBanner.updateFile(newFile);
    }

    private void updateMobileFile(MainBottomBanner mainBottomBanner, MultipartFile file) {
        if (Objects.isNull(file)) return;
        if (mainBottomBanner.getMobileFile() != null) fileHelper.deleteFile(mainBottomBanner.getMobileFile());
        File newFile = fileHelper.createFileAndSave(MAIN_BOTTOM_BANNER_S3_PATH, file);
        mainBottomBanner.updateMobilFile(newFile);
    }
}
