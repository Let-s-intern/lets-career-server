package org.letscareer.letscareer.domain.banner.mapper;

import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.entity.Popup;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.file.entity.File;
import org.springframework.stereotype.Component;

@Component
public class PopupMapper {
    public Popup toEntity(BannerType type, CreateBannerRequestDto createBannerRequestDto, File file) {
        return Popup.createPopup(type, createBannerRequestDto, file);
    }
}
