package org.letscareer.letscareer.domain.banner.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.file.entity.File;

import static org.letscareer.letscareer.global.common.utils.EntityUpdateValueUtils.updateValue;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = BannerType.Values.PROGRAM)
public class ProgramBanner extends Banner {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    @Builder(access = AccessLevel.PRIVATE)
    private ProgramBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto, File file) {
        super(type, createBannerRequestDto);
        this.file = file;
    }

    public static ProgramBanner createProgramBanner(BannerType type, CreateBannerRequestDto createBannerRequestDto, File file) {
        return ProgramBanner.builder()
                .type(type)
                .createBannerRequestDto(createBannerRequestDto)
                .file(file)
                .build();
    }

    public void updateProgramBanner(UpdateBannerRequestDto updateBannerRequestDto) {
        super.updateBanner(updateBannerRequestDto);
    }

    public void updateFile(File file) {
        this.file = updateValue(this.file, file);
    }
}
