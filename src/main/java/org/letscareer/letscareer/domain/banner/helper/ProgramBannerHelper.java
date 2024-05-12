package org.letscareer.letscareer.domain.banner.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.entity.ProgramBanner;
import org.letscareer.letscareer.domain.banner.repository.ProgramBannerRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class ProgramBannerHelper {
    private final ProgramBannerRepository programBannerRepository;

    public void saveProgramBanner(ProgramBanner programBanner) {
        programBannerRepository.save(programBanner);
    }
}
