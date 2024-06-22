package org.letscareer.letscareer.domain.banner.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.entity.Popup;
import org.letscareer.letscareer.domain.banner.error.BannerErrorCode;
import org.letscareer.letscareer.domain.banner.repository.PopupRepository;
import org.letscareer.letscareer.domain.banner.vo.PopupBannerAdminDetailVo;
import org.letscareer.letscareer.domain.banner.vo.PopupBannerAdminVo;
import org.letscareer.letscareer.domain.banner.vo.PopupUserVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PopupHelper {
    private final PopupRepository popupRepository;

    public void savePopup(Popup popup) {
        popupRepository.save(popup);
    }

    public List<PopupBannerAdminVo> findAllPopupAdminVos() {
        return popupRepository.findAllPopupAdminVos();
    }

    public Popup findByIdOrThrow(Long bannerId) {
        return popupRepository.findById(bannerId).orElseThrow(() -> new EntityNotFoundException(BannerErrorCode.BANNER_NOT_FOUND));
    }

    public PopupBannerAdminDetailVo findBannerAdminDetailVoOrThrow(Long bannerId) {
        return popupRepository.findBannerAdminDetailVo(bannerId)
                .orElseThrow(() -> new EntityNotFoundException(BannerErrorCode.BANNER_NOT_FOUND));
    }

    public List<PopupUserVo> findAllPopBannerUserVos() {
        return popupRepository.findAllPopBannerUserVos();
    }

    public void deletePopup(Popup popup) {
        popupRepository.delete(popup);
    }
}
