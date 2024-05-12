package org.letscareer.letscareer.domain.banner.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.entity.Popup;
import org.letscareer.letscareer.domain.banner.helper.PopupHelper;
import org.letscareer.letscareer.domain.banner.mapper.PopupMapper;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("POPUP")
public class PopupServiceImpl implements BannerService {
    private final PopupHelper popupHelper;
    private final PopupMapper popupMapper;

    @Override
    public void createBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        Popup newPopup = popupMapper.toEntity(type, createBannerRequestDto);
        popupHelper.savePopup(newPopup);
    }
}
