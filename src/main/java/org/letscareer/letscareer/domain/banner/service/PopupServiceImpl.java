package org.letscareer.letscareer.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.response.BannerAdminListResponseDto;
import org.letscareer.letscareer.domain.banner.entity.Popup;
import org.letscareer.letscareer.domain.banner.helper.PopupHelper;
import org.letscareer.letscareer.domain.banner.mapper.BannerMapper;
import org.letscareer.letscareer.domain.banner.mapper.PopupMapper;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
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
    public BannerAdminListResponseDto getBannersForAdmin() {
        List<BannerAdminVo> popupAdminList = popupHelper.findAllPopupAdminVos();
        return bannerMapper.toBannerAdminListResponseDto(popupAdminList);
    }
}
