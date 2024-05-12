package org.letscareer.letscareer.domain.banner.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.entity.Popup;
import org.letscareer.letscareer.domain.banner.repository.PopupRepository;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Component
public class PopupHelper {
    private final PopupRepository popupRepository;

    public void savePopup(Popup popup) {
        popupRepository.save(popup);
    }

    public List<BannerAdminVo> findAllPopupAdminVos() {
        return popupRepository.findAllPopupAdminVos();
    }
}
