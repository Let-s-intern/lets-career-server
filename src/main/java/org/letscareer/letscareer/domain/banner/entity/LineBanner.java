package org.letscareer.letscareer.domain.banner.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("line")
public class LineBanner extends Banner {
    @NotNull
    private String contents;

    @NotNull
    @Column(length = 7)
    private String colorCode;

    @NotNull
    @Column(length = 7)
    private String textColorCode;
}
