package org.letscareer.letscareer.domain.banner.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.entity.LineBanner;
import org.letscareer.letscareer.domain.banner.repository.LineBannerRepository;
import org.letscareer.letscareer.domain.banner.vo.LineBannerAdminVo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Component
public class LineBannerHelper {
    private final LineBannerRepository lineBannerRepository;

    public void saveLineBanner(LineBanner lineBanner) {
        lineBannerRepository.save(lineBanner);
    }

    public List<LineBannerAdminVo> findAllLineBannerAdminVos() {
        return lineBannerRepository.findAllLineBannerAdminVos();
    }
}
