package org.letscareer.letscareer.domain.banner.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.entity.ProgramBanner;
import org.letscareer.letscareer.domain.banner.repository.ProgramBannerRepository;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Component
public class ProgramBannerHelper {
    private final ProgramBannerRepository programBannerRepository;

    public void saveProgramBanner(ProgramBanner programBanner) {
        programBannerRepository.save(programBanner);
    }

    public List<BannerAdminVo> findAllProgramBannerAdminVos() {
        return programBannerRepository.findAllProgramBannerAdminVos();
    }
}
