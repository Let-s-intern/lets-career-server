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
@Service("POPUP")
public class PopupServiceImpl implements BannerService {
    private final PopupHelper popupHelper;
    private final PopupMapper popupMapper;
    private final BannerMapper bannerMapper;
    private final FileHelper fileHelper;
    private static final String POPUP_BANNER_S3_PATH = "banner/popup/";

    @Override
    public void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto, MultipartFile file) {
        File newFile = fileHelper.createFileAndSave(POPUP_BANNER_S3_PATH, file);
        Popup newPopup = popupMapper.toEntity(type, createBannerRequestDto, newFile);
        popupHelper.savePopup(newPopup);
    }

    @Override
    public BannerListResponseDto getBanners() {
        List<BannerUserVo> bannerUserVos = popupHelper.findAllPopBannerUserVos();
        return bannerMapper.toBannerAdminListResponseDto(bannerUserVos);
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
    public void updateBanner(Long bannerId, UpdateBannerRequestDto updateBannerRequestDto, MultipartFile file) {
        Popup popup = popupHelper.findByIdOrThrow(bannerId);
        updateFile(popup, file);
        popup.updatePopup(updateBannerRequestDto);
    }

    @Override
    public void deleteBanner(Long bannerId) {
        Popup popup = popupHelper.findByIdOrThrow(bannerId);
        popupHelper.deletePopup(popup);
    }

    private void updateFile(Popup popup, MultipartFile file) {
        if (Objects.isNull(file)) return;
        if (popup.getFile() != null) fileHelper.deleteFile(popup.getFile());
        File newFile = fileHelper.createFileAndSave(POPUP_BANNER_S3_PATH, file);
        popup.updateFile(newFile);
    }
}
