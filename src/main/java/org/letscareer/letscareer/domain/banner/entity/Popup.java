package org.letscareer.letscareer.domain.banner.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "Popup")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("popup")
public class Popup extends Banner {
    @NotNull
    private String imgUrl;
}
