package org.letscareer.letscareer.domain.banner.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.file.entity.File;

import java.util.Objects;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = BannerType.Values.PROGRAM)
public class ProgramBanner extends Banner {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mobile_file_id")
    private File mobileFile;

    @Builder(access = AccessLevel.PRIVATE)
    private ProgramBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto, File file, File newMobileFile) {
        super(type, createBannerRequestDto);
        this.file = file;
        this.mobileFile = newMobileFile;
    }

    public static ProgramBanner createProgramBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto, File file, File newMobileFile) {
        return ProgramBanner.builder()
                .type(type)
                .createBannerRequestDto(createBannerRequestDto)
                .file(file)
                .newMobileFile(newMobileFile)
                .build();
    }

    public void updateProgramBanner(UpdateBannerRequestDto updateBannerRequestDto) {
        if(Objects.isNull(updateBannerRequestDto)) return;
        super.updateBanner(updateBannerRequestDto);
    }

    public void updateFile(File file) {
        this.file = updateValue(this.file, file);
    }

    public void updateMobileFile(File file) {
        this.mobileFile = updateValue(this.mobileFile, file);
    }
}
