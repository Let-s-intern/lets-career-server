package org.letscareer.letscareer.domain.coupon.type.converter;

import jakarta.persistence.Converter;
import org.letscareer.letscareer.domain.coupon.type.CouponProgramType;
import org.letscareer.letscareer.global.common.utils.AbstractEnumCodeAttributeConverter;

@Converter
public class CouponProgramTypeConverter extends AbstractEnumCodeAttributeConverter<CouponProgramType> {
    public CouponProgramTypeConverter() {
        super(CouponProgramType.class);
    }
}

