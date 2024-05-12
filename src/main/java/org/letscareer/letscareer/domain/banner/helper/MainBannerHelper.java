package org.letscareer.letscareer.domain.banner.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.entity.MainBanner;
import org.letscareer.letscareer.domain.banner.repository.MainBannerRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class MainBannerHelper {
    private final MainBannerRepository mainBannerRepository;

    public void saveMainBanner(MainBanner mainBanner) {
        mainBannerRepository.save(mainBanner);
    }
}
