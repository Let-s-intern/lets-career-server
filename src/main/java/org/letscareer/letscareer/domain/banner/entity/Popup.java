package org.letscareer.letscareer.domain.banner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.domain.file.entity.File;

import java.util.Objects;

import static org.letscareer.letscareer.global.common.utils.EntityUpdateValueUtils.updateValue;

@Entity(name = "Popup")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = BannerType.Values.POPUP)
public class Popup extends Banner {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    @Builder(access = AccessLevel.PRIVATE)
    private Popup(BannerType type, CreateBannerRequestDto createBannerRequestDto, File file) {
        super(type, createBannerRequestDto);
        this.file = file;
    }

    public static Popup createPopup(BannerType type, CreateBannerRequestDto createBannerRequestDto, File file) {
        return Popup.builder()
                .type(type)
                .createBannerRequestDto(createBannerRequestDto)
                .file(file)
                .build();
    }

    public void updatePopup(UpdateBannerRequestDto updateBannerRequestDto) {
        if(Objects.isNull(updateBannerRequestDto)) return;
        super.updateBanner(updateBannerRequestDto);
    }

    public void updateFile(File file) {
        this.file = updateValue(this.file, file);
    }
}
