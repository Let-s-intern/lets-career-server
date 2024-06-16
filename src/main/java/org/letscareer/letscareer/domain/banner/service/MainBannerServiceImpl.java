package org.letscareer.letscareer.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerDetailResponseDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerListResponseDto;
import org.letscareer.letscareer.domain.banner.entity.MainBanner;
import org.letscareer.letscareer.domain.banner.helper.MainBannerHelper;
import org.letscareer.letscareer.domain.banner.mapper.BannerMapper;
import org.letscareer.letscareer.domain.banner.mapper.MainBannerMapper;
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
@Service("MAIN")
public class MainBannerServiceImpl implements BannerService {
    private final MainBannerHelper mainBannerHelper;
    private final MainBannerMapper mainBannerMapper;
    private final BannerMapper bannerMapper;
    private final FileHelper fileHelper;
    private static final String MAIN_BANNER_S3_PATH = "banner/main/";

    @Override
    public void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto, MultipartFile file) {
        File newFile = fileHelper.createFileAndSave(MAIN_BANNER_S3_PATH, file);
        MainBanner newMainBanner = mainBannerMapper.toEntity(type, createBannerRequestDto, newFile);
        mainBannerHelper.saveMainBanner(newMainBanner);
    }

    @Override
    public BannerListResponseDto getBanners() {
        List<BannerUserVo> bannerUserVos = mainBannerHelper.findAllUserBannerAdminVos();
        return bannerMapper.toBannerAdminListResponseDto(bannerUserVos);
    }

    @Override
    public BannerListResponseDto getBannersForAdmin() {
        List<BannerAdminVo> mainBannerAdminList = mainBannerHelper.findAllMainBannerAdminVos();
        return bannerMapper.toBannerAdminListResponseDto(mainBannerAdminList);
    }

    @Override
    public BannerDetailResponseDto getBannerDetail(Long bannerId) {
        BannerAdminDetailVo bannerAdminDetailVo = mainBannerHelper.findBannerAdminDetailVoByIdOrThrow(bannerId);
        return bannerMapper.toBannerDetailResponseDto(bannerAdminDetailVo);
    }

    @Override
    public void updateBanner(Long bannerId, UpdateBannerRequestDto updateBannerRequestDto, MultipartFile file) {
        MainBanner mainBanner = mainBannerHelper.findByIdOrThrow(bannerId);
        updateFile(mainBanner, file);
        mainBanner.updateMainBanner(updateBannerRequestDto);
    }

    @Override
    public void deleteBanner(Long bannerId) {
        MainBanner mainBanner = mainBannerHelper.findByIdOrThrow(bannerId);
        mainBannerHelper.deleteMainBanner(mainBanner);
    }

    private void updateFile(MainBanner mainBanner, MultipartFile file) {
        if (Objects.isNull(file)) return;
        if (mainBanner.getFile() != null) fileHelper.deleteFile(mainBanner.getFile());
        File newFile = fileHelper.createFileAndSave(MAIN_BANNER_S3_PATH, file);
        mainBanner.updateFile(newFile);
    }
}
