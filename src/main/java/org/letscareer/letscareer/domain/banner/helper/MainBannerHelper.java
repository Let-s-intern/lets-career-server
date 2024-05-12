package org.letscareer.letscareer.domain.banner.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.entity.MainBanner;
import org.letscareer.letscareer.domain.banner.error.BannerErrorCode;
import org.letscareer.letscareer.domain.banner.repository.MainBannerRepository;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Component
public class MainBannerHelper {
    private final MainBannerRepository mainBannerRepository;

    public void saveMainBanner(MainBanner mainBanner) {
        mainBannerRepository.save(mainBanner);
    }

    public List<BannerAdminVo> findAllMainBannerAdminVos() {
        return mainBannerRepository.findAllMainBannerAdminVos();
    }

    public MainBanner findByIdOrThrow(Long bannerId) {
        return mainBannerRepository.findById(bannerId).orElseThrow(() -> new EntityNotFoundException(BannerErrorCode.BANNER_NOT_FOUND));
    }
}
