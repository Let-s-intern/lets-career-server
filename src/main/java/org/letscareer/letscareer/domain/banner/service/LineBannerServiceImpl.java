package org.letscareer.letscareer.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerDetailResponseDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerListResponseDto;
import org.letscareer.letscareer.domain.banner.entity.LineBanner;
import org.letscareer.letscareer.domain.banner.helper.LineBannerHelper;
import org.letscareer.letscareer.domain.banner.mapper.BannerMapper;
import org.letscareer.letscareer.domain.banner.mapper.LineBannerMapper;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.banner.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service("LINE")
public class LineBannerServiceImpl implements BannerService {
    private final LineBannerHelper lineBannerHelper;
    private final LineBannerMapper lineBannerMapper;
    private final BannerMapper bannerMapper;

    @Override
    public void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto, MultipartFile file) {
        LineBanner newLineBanner = lineBannerMapper.toEntity(type, createBannerRequestDto);
        lineBannerHelper.saveLineBanner(newLineBanner);
    }

    @Override
    public BannerListResponseDto getBanners() {
        List<LineBannerUserVo> lineBannerAdminList = lineBannerHelper.findAllLineBannerUserVos();
        return bannerMapper.toBannerAdminListResponseDto(lineBannerAdminList);
    }

    @Override
    public BannerListResponseDto getBannersForAdmin() {
        List<LineBannerAdminVo> lineBannerAdminList = lineBannerHelper.findAllLineBannerAdminVos();
        return bannerMapper.toBannerAdminListResponseDto(lineBannerAdminList);
    }

    @Override
    public BannerDetailResponseDto getBannerDetail(Long bannerId) {
        LineBannerAdminDetailVo lineBannerAdminDetailVo = lineBannerHelper.findLineBannerAdminDetailVoByIdOrThrow(bannerId);
        return bannerMapper.toBannerDetailResponseDto(lineBannerAdminDetailVo);
    }

    @Override
    public void updateBanner(Long bannerId, UpdateBannerRequestDto updateBannerRequestDto, MultipartFile file) {
        LineBanner lineBanner = lineBannerHelper.findLineBannerByIdOrThrow(bannerId);
        lineBanner.updateLineBanner(updateBannerRequestDto);
    }

    @Override
    public void deleteBanner(Long bannerId) {
        LineBanner lineBanner = lineBannerHelper.findLineBannerByIdOrThrow(bannerId);
        lineBannerHelper.deleteLineBanner(lineBanner);
    }
}
