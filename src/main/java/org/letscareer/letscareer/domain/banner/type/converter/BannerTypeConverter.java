package org.letscareer.letscareer.domain.banner.type.converter;

import org.letscareer.letscareer.domain.banner.type.BannerType;
import org.letscareer.letscareer.global.common.utils.enm.AbstractEnumCodeAttributeConverter;

public class BannerTypeConverter extends AbstractEnumCodeAttributeConverter<BannerType> {
    public BannerTypeConverter() {
        super(BannerType.class);
    }
}
