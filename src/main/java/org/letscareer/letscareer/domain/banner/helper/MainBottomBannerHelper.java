package org.letscareer.letscareer.domain.banner.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.entity.MainBanner;
import org.letscareer.letscareer.domain.banner.entity.MainBottomBanner;
import org.letscareer.letscareer.domain.banner.error.BannerErrorCode;
import org.letscareer.letscareer.domain.banner.repository.MainBottomBannerRepository;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminDetailVo;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.letscareer.letscareer.domain.banner.vo.BannerUserVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MainBottomBannerHelper {
    private final MainBottomBannerRepository mainBottomBannerRepository;

    public void saveMainBottomBanner(MainBottomBanner mainBottomBanner) {
        mainBottomBannerRepository.save(mainBottomBanner);
    }

    public List<BannerAdminVo> findAllMainBottomBannerAdminVos() {
        return mainBottomBannerRepository.findAllMainBottomBannerAdminVos();
    }

    public List<BannerUserVo> findAllUserBannerAdminVos() {
        return mainBottomBannerRepository.findAllMainBottomBannerUserVos();
    }

    public MainBottomBanner findByIdOrThrow(Long bannerId) {
        return mainBottomBannerRepository.findById(bannerId).orElseThrow(() -> new EntityNotFoundException(BannerErrorCode.BANNER_NOT_FOUND));
    }

    public BannerAdminDetailVo findBannerAdminDetailVoByIdOrThrow(Long bannerId) {
        return mainBottomBannerRepository.findBannerAdminDetailVo(bannerId)
                .orElseThrow(() -> new EntityNotFoundException(BannerErrorCode.BANNER_NOT_FOUND));
    }

    public void deleteMainBottomBanner(MainBottomBanner mainBottomBanner) {
        mainBottomBannerRepository.delete(mainBottomBanner);
    }
}
