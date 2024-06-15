package org.letscareer.letscareer.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerDetailResponseDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerListResponseDto;
import org.letscareer.letscareer.domain.banner.entity.Popup;
import org.letscareer.letscareer.domain.banner.helper.PopupHelper;
import org.letscareer.letscareer.domain.banner.mapper.BannerMapper;
import org.letscareer.letscareer.domain.banner.mapper.PopupMapper;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminDetailVo;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service("POPUP")
public class PopupServiceImpl implements BannerService {
    private final PopupHelper popupHelper;
    private final PopupMapper popupMapper;
    private final BannerMapper bannerMapper;

    @Override
    public void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        Popup newPopup = popupMapper.toEntity(type, createBannerRequestDto);
        popupHelper.savePopup(newPopup);
    }

    @Override
    public BannerListResponseDto getBanners() {
        return null;
    }

    @Override
    public BannerListResponseDto getBannersForAdmin() {
        List<BannerAdminVo> popupAdminList = popupHelper.findAllPopupAdminVos();
        return bannerMapper.toBannerAdminListResponseDto(popupAdminList);
    }

    @Override
    public BannerDetailResponseDto getBannerDetail(Long bannerId) {
        BannerAdminDetailVo bannerAdminDetailVo = popupHelper.findBannerAdminDetailVoOrThrow(bannerId);
        return bannerMapper.toBannerDetailResponseDto(bannerAdminDetailVo);
    }

    @Override
    public void updateBanner(Long bannerId, UpdateBannerRequestDto updateBannerRequestDto) {
        Popup popup = popupHelper.findByIdOrThrow(bannerId);
        popup.updatePopup(updateBannerRequestDto);
    }

    @Override
    public void deleteBanner(Long bannerId) {
        Popup popup = popupHelper.findByIdOrThrow(bannerId);
        popupHelper.deletePopup(popup);
    }

}
