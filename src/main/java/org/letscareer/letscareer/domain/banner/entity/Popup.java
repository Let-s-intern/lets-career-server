package org.letscareer.letscareer.domain.banner.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.banner.dto.request.CreateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.dto.request.UpdateBannerRequestDto;
import org.letscareer.letscareer.domain.banner.type.BannerType;

import static org.letscareer.letscareer.global.common.utils.EntityUpdateValueUtils.updateValue;

@Entity(name = "Popup")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = BannerType.Values.POPUP)
public class Popup extends Banner {
    @NotNull
    private String imgUrl;

    @Builder(access = AccessLevel.PRIVATE)
    private Popup(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        super(type, createBannerRequestDto);
        this.imgUrl = createBannerRequestDto.imgUrl();
    }

    public static Popup createPopup(BannerType type, CreateBannerRequestDto createBannerRequestDto) {
        return Popup.builder()
                .type(type)
                .createBannerRequestDto(createBannerRequestDto)
                .build();
    }

    public void updatePopup(UpdateBannerRequestDto updateBannerRequestDto) {
        super.updateBanner(updateBannerRequestDto);
        this.imgUrl = updateValue(this.imgUrl, updateBannerRequestDto.imgUrl());
    }
}
