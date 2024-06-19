package org.letscareer.letscareer.domain.banner.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.entity.ProgramBanner;
import org.letscareer.letscareer.domain.banner.error.BannerErrorCode;
import org.letscareer.letscareer.domain.banner.repository.ProgramBannerRepository;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminDetailVo;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProgramBannerHelper {
    private final ProgramBannerRepository programBannerRepository;

    public void saveProgramBanner(ProgramBanner programBanner) {
        programBannerRepository.save(programBanner);
    }

    public List<BannerAdminVo> findAllProgramBannerAdminVos() {
        return programBannerRepository.findAllProgramBannerAdminVos();
    }

    public ProgramBanner findByIdOrThrow(Long bannerId) {
        return programBannerRepository.findById(bannerId).orElseThrow(() -> new EntityNotFoundException(BannerErrorCode.BANNER_NOT_FOUND));
    }

    public BannerAdminDetailVo findBannerAdminDetailVoOrThrow(Long bannerId) {
        return programBannerRepository.findBannerAdminDetailVo(bannerId)
                .orElseThrow(() -> new EntityNotFoundException(BannerErrorCode.BANNER_NOT_FOUND));
    }

    public void deleteProgramBanner(ProgramBanner programBanner) {
        programBannerRepository.delete(programBanner);
    }
}
